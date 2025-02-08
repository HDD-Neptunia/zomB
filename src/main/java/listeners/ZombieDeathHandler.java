package listeners;

import bulk.WaveManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "zombie1")
public class ZombieDeathHandler {

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event.getEntity() instanceof Zombie) {
			ServerLevel level = (ServerLevel) event.getEntity().level;
			Player player = level.getNearestPlayer(event.getEntity(), 50);
			
			if(player != null) {
				WaveManager.onZombieKilled(level, player);
			}
		}
	}
}
