package waves;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class WaveCommand {
	
	public static void
register(CommandDispatcher<CommandSourceStack> dispatcher) {
		
		dispatcher.register(Commands.literal("startwaves")
				.executes(context -> {
					WaveManager.startWaves(); //Start Wave Spawning
				return 1;
				}));
	}
}
