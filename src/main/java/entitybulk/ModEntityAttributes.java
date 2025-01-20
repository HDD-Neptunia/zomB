package entitybulk;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import zombies.CustomZombie;
import zombies.FastZombie;

@Mod.EventBusSubscriber(modid = "zombie1", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {
	
	@SubscribeEvent
	public void registerAttributes(EntityAttributeCreationEvent event) {
		
		event.put(EntityInit.CZOMBIE.get(), CustomZombie.createAttributes().build());
		event.put(EntityInit.FASTZOMBIE.get(), FastZombie.createAttributes().build());
	}
}




// check check cehck entity attributes