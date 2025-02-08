package networking;

import java.util.function.Supplier;

import bulk.WaveManager;
import gui.onRenderHUD;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ZombieDeathPacket {
	
	private int currentRound;
	private final int zombiesLeft;
	
	public ZombieDeathPacket(int currentRound, int zombiesLeft) {
		this.currentRound = currentRound;;
		this.zombiesLeft = WaveManager.zombiesRemaining;
	}
	
	public ZombieDeathPacket(FriendlyByteBuf buf) {
		this.currentRound = buf.readInt();
		this.zombiesLeft = buf.readInt();
	}
	
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(currentRound);
		buf.writeInt(zombiesLeft);
		
	}
	
	public static void handle(ZombieDeathPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if(ctx.get().getDirection().getReceptionSide().isClient()) {
				onRenderHUD.updateRound(packet.currentRound, packet.zombiesLeft);
				
			}
		});
		ctx.get().setPacketHandled(true);
 	}
}
