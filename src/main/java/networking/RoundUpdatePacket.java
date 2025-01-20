package networking;

import java.util.function.Supplier;

import gui.GUICore;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class RoundUpdatePacket {
	
	private final int round;
	public static int zombiesLeft;
	
	
	public RoundUpdatePacket(int round, int zombiesLeft) {
		this.round = round;
		this.zombiesLeft = zombiesLeft;
	}

	
	public RoundUpdatePacket(FriendlyByteBuf buf) {
		
			this.round = buf.readInt();
			this.zombiesLeft = buf.readInt();
		}
	
	
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(round);
		buf.writeInt(zombiesLeft);
	}
	
			
	
	public static void handle(RoundUpdatePacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if(ctx.get().getDirection().getReceptionSide().isClient()) {
			GUICore.updateRound(packet.round);
			GUICore.updateRound(packet.zombiesLeft);
			}
		});
		ctx.get().setPacketHandled(true);

	}
	
	public int getRound() {
		return round;
}
	
	public int getZombiesleft() {
		return zombiesLeft;
	}

}