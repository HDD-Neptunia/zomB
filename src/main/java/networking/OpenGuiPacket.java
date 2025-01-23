//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package networking;

import java.util.function.Supplier;

import gui.GUICore;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.network.NetworkEvent;

public class OpenGuiPacket {

	public OpenGuiPacket() {}
	
	//encoding is unnecessary but required by forge
	public static void encode(OpenGuiPacket msg, FriendlyByteBuf buf) {
		
	}
	
	public static OpenGuiPacket decode(FriendlyByteBuf buf) {
		return new OpenGuiPacket();
	}
	
	
	public static void handle(OpenGuiPacket msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			
			if(ctx.get().getDirection().getReceptionSide().isClient()) {
			Minecraft.getInstance().setScreen(new GUICore(new TextComponent("Wave")));
			}
		});
		ctx.get().setPacketHandled(true);
	} //Handle method is client only due to Minecraft.getInstance()
}
