package pointsmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;
import networking.Networking;
import networking.PointsPacket;
import zombies.CustomZombie;

@EventBusSubscriber
public class PointsManager {
	private static final HashMap<UUID, Integer> playerScore = new HashMap<>();
	private static final int HIT_POINTS = 15;
	private static final int KILL_POINTS = 135;

	
	//Points method
	public static void addPoints(ServerPlayer player, int points) {
		UUID playerUUID = player.getUUID();
		playerScore.put(playerUUID, playerScore.getOrDefault(playerUUID, 0) + points);
	}
	
	//Method to retrieve player score
	public static int getPlayerScore(ServerPlayer player) {
		return playerScore.getOrDefault(player.getUUID(), 0);
	}
	
	@SubscribeEvent
	public static void onZombieHit(LivingDamageEvent event) {
		if(event.getEntity() instanceof CustomZombie && event.getSource().getEntity() instanceof ServerPlayer player) {
			addPoints(player, HIT_POINTS);
			sendScoresToClients(player.server);
		}
	}
	
	@SubscribeEvent
	public static void onZombieKill(LivingDeathEvent event) {
		if (event.getEntity() instanceof CustomZombie && event.getSource().getEntity() instanceof ServerPlayer player) {
			addPoints(player, KILL_POINTS);
			sendScoresToClients(player.server);
		}
	}
	
	private static void sendScoresToClients(MinecraftServer server) {
		Map<UUID, Integer> playerScores = new HashMap<>();
		for (ServerPlayer player : server.getPlayerList().getPlayers()) {
			playerScores.put(player.getUUID(), getPlayerScore(player));
		}
		Networking.CHANNEL.send(PacketDistributor.ALL.noArg(), new PointsPacket(playerScores));
	}
}
