package networking;

import java.util.List;
import java.util.function.Supplier;


import bulk.SpawnerLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

public class CompleteRoundPacket {


private int zombiesRemaining;
private BlockPos playerPos;
private ResourceKey<Level> levelKey;
private List<BlockPos> allSpawners;
private int currentRound;

	public CompleteRoundPacket(int zombiesRemaining, int currentRound, ResourceKey<Level> levelKey, BlockPos playerPos, List<BlockPos> allSpawners, ServerPlayer player) {
		this.zombiesRemaining = zombiesRemaining;
		this.currentRound = currentRound;
		this.levelKey = levelKey;
		this.playerPos = playerPos;
		this.allSpawners = allSpawners;
	}
	
	public CompleteRoundPacket(FriendlyByteBuf buf) {
		this.zombiesRemaining = buf.readInt();
		this.currentRound = buf.readInt();
		this.levelKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
		this.playerPos = buf.readBlockPos();
	}
	
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(zombiesRemaining);
		buf.writeInt(currentRound);
		buf.writeResourceLocation(levelKey.location());
		buf.writeBlockPos(playerPos);
		}
	
	public static void handle(CompleteRoundPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if(ctx.get().getDirection().getReceptionSide().isClient()) {
				ServerLevel level = ctx.get().getSender().server.getLevel(packet.levelKey);
				
				if (level != null) {
				SpawnerLogic.completeRound(packet.zombiesRemaining, packet.currentRound, level, ctx.get().getSender(), packet.playerPos, packet.allSpawners);
				}
			}
		});
		ctx.get().setPacketHandled(true);
	}
	
	public int getZombiesRemaining() {
		return zombiesRemaining;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
}
