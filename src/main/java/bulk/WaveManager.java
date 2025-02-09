//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package bulk;

import java.util.List;

import gui.onRenderHUD;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;
import networking.Networking;
import networking.RoundUpdatePacket;
import networking.ZombieDeathPacket;

public class WaveManager {
	
	private static int roundNumber = 0;
	public static int currentRound = 0;
	public static int zombiesRemaining;


	public static void startWave() {
		currentRound++;
		zombiesRemaining = calculateZombiesForWave(currentRound);
		System.out.println("Starting wave " + currentRound);
	}
	
	private static int calculateZombiesForWave(int roundNumber) {
		
		if (roundNumber>1) {
			return 7+ (roundNumber*4);
		}
		else return 7; //Special zombies do not count toward this
	}   
	
	public static void onZombieKilled(ServerLevel level, Player player) {
		
		System.out.println("debug");
			
			zombiesRemaining--;
			onRenderHUD.updateRound(currentRound, zombiesRemaining);
			
			
			ServerPlayer serverPlayer = (ServerPlayer) player;
			Networking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer),
					new ZombieDeathPacket(currentRound, zombiesRemaining));
			
			if(zombiesRemaining == 0) {
				startWave();
				
				BlockPos playerPos = player.blockPosition();
				List<BlockPos> spawners = SpawnerLogic.findSpawnersInRange(level, playerPos, 50);
				SpawnerLogic.startRound(level, playerPos, spawners);

			}			
		}		
	}




//Interval between rounds
//Zombies spawn one at a time. Interval between spawns, so they trickle in rather than spawning in all at once.
//Update to nearest 4 spawners
//Mystery barrel