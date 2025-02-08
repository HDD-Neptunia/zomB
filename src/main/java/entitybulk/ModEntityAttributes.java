//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package entitybulk;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zombies.CustomZombie;
import zombies.FastZombie;
import zombies.IceZombie;

@Mod.EventBusSubscriber(modid = "zombie1", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {

	@SubscribeEvent
	public static void onAttributeCreation(EntityAttributeCreationEvent event) {
		
		event.put(EntityInit.CZOMBIE.get(), CustomZombie.createAttributes().build());
		event.put(EntityInit.FASTZOMBIE.get(), FastZombie.createAttributes().build());
		event.put(EntityInit.ICEZOMBIE.get(), IceZombie.createAttributes().build());
	}
	
	public static void register(IEventBus eventBus) {
	
	}  
}