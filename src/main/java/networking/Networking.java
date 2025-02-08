//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {
	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation("zombie1", "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);
	
	public static void register() {
		CHANNEL.registerMessage(2, 
			OpenGuiPacket.class, 
			OpenGuiPacket::encode, 
			OpenGuiPacket::decode, 
			OpenGuiPacket::handle);
		
		CHANNEL.registerMessage(3,
			RoundUpdatePacket.class,
			RoundUpdatePacket::encode,
			RoundUpdatePacket::new,
			RoundUpdatePacket::handle);
		
		CHANNEL.registerMessage(4,
			ZombieDeathPacket.class,
			ZombieDeathPacket::encode,
			ZombieDeathPacket::new,
			ZombieDeathPacket::handle);
		
		CHANNEL.registerMessage(5,
			CompleteRoundPacket.class,
			CompleteRoundPacket::encode,
			CompleteRoundPacket::new,
			CompleteRoundPacket::handle);
	}
}