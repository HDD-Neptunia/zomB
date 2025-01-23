//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package bulk;

import com.O.zomB.Zombie1;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Zombie1.MODID)
public class ModCommands {

	@SubscribeEvent
	public static void onRegisterCommands(RegisterCommandsEvent event) {
		StartWaveCommand.register(event.getDispatcher());
	}
}
