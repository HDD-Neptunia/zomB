//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package bulk;

import gui.onRenderHUD;
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
		zombiesRemaining = calculateZombiesForWave(roundNumber);
		System.out.println("Starting wave " + currentRound);
	}
	
	private static int calculateZombiesForWave(int roundNumber) {
		return 7 + (roundNumber*4); //Special zombies do not count toward this
	}   
	
	public static void onZombieKilled(ServerLevel level, Player player) {
		
		System.out.println("debug");
			
			zombiesRemaining--;
			onRenderHUD.updateRound(currentRound, zombiesRemaining);
			
			
			ServerPlayer serverPlayer = (ServerPlayer) player;
			Networking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer),
					new ZombieDeathPacket(currentRound, zombiesRemaining));
			
			if(zombiesRemaining == 0) {
				roundNumber++;
				onRenderHUD.updateRound(currentRound, zombiesRemaining);

			}			
		}			//Zombies past round 1 aren't spawning..
	}