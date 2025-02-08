//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package bulk;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import networking.Networking;
import networking.OpenGuiPacket;
import networking.RoundUpdatePacket;


public class StartWaveCommand {
	
	//Register the command
	public static int execute(CommandContext<CommandSourceStack> context) {
		try {
		ServerPlayer player = context.getSource().getPlayerOrException();
		
		Networking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), 
				new RoundUpdatePacket(WaveManager.currentRound, WaveManager.zombiesRemaining));
		
		} catch (CommandSyntaxException e) {
			context.getSource().sendFailure(new TextComponent("can be run by a player"));
			}
		return 1;
	}
	
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(
				Commands.literal("startwave")	
					.executes(context -> {
						WaveManager.startWave();
					
						ServerLevel level = context.getSource().getLevel();
						ServerPlayer player = context.getSource().getPlayerOrException();
						BlockPos playerPos = player.blockPosition();
						List<BlockPos> allSpawners = SpawnerLogic.findSpawnersInRange(level, playerPos, 10);
						
						
					SpawnerLogic.startRound(level, playerPos, allSpawners);
					//Make GUI visible
				
					
					Networking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
							new RoundUpdatePacket(WaveManager.currentRound, WaveManager.zombiesRemaining));
				
					//Aaand render!
		//	Minecraft.getInstance().setScreen(new GUICore(new TextComponent("Wave")));
					return execute(context);
			})
		);
	}
}


