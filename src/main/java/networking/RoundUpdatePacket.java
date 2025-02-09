//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package networking;

import java.util.function.Supplier;

import bulk.WaveManager;
import gui.onRenderHUD;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;


public class RoundUpdatePacket {
	
	public final int round;
	public final int zombiesLeft ;
	
	public RoundUpdatePacket(int round, int zombiesLeft) {
		this.round = WaveManager.currentRound;
		this.zombiesLeft = WaveManager.zombiesRemaining;
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
				onRenderHUD.updateRound(packet.round, packet.zombiesLeft);
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