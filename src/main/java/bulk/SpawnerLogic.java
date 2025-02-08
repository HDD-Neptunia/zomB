//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package bulk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import blocks.CustomZombieSpawnerBlock;
import entitybulk.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import networking.Networking;
import networking.RoundUpdatePacket;
import zombies.CustomZombie;
import zombies.ZombieTypes;

public class SpawnerLogic {
	private static final Random random = new Random();
	static final List<Mob> activeZombies = new ArrayList<>();
	static int currentRound = 0;
	private static int zombiesPerRound = 7;
	private static int totalSpecialUnits;
	private static int totalZombies = zombiesPerRound;
	private static int maxZombiesOnField = 75;
	
	public static int totalZombies() {
		return zombiesPerRound;
	}

	public static int activeRound() {
		return currentRound;
	}
	
	
	
	public static void startRound(ServerLevel level, BlockPos playerPos, List<BlockPos> allSpawners) {
		currentRound++;
		zombiesPerRound += 4;
		
		List<BlockPos> nearestSpawners = findSpawnersInRange(level, playerPos, 50);
		spawnZombies(level, playerPos, nearestSpawners);
	}
	
	public static void completeRound(int zombiesRemaining, int currentRound, ServerLevel level, ServerPlayer player, BlockPos playerPos, List<BlockPos> allSpawners) {
		if(zombiesRemaining==0) {
			WaveManager.currentRound++;
			SpawnerLogic.currentRound = WaveManager.currentRound;
			
			spawnZombies(level, playerPos, allSpawners);
			
			ServerPlayer serverPlayer = (ServerPlayer) player;
			Networking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer),
					new RoundUpdatePacket(SpawnerLogic.currentRound, zombiesRemaining));
	}
}
	
	 public static void activateNearestSpawners(ServerLevel level, BlockPos playerPos) {
		
		//Find player pos
				
		if (playerPos == null) return;
		
		//Find all spawners in range
		List<BlockPos> spawners = findSpawnersInRange(level, playerPos, 50);
		spawnZombies(level, playerPos, spawners);
		
		spawners.sort(Comparator.comparingDouble(spawner -> spawner.distSqr(playerPos)));
		if(spawners.size() >= 2) {
			spawnZombiesBetween(spawners.get(0), spawners.get(1));
		}
	} 
	
	
	
	static List<BlockPos> findSpawnersInRange(ServerLevel level, BlockPos playerPos, int range) {

		List<BlockPos> spawnerPositions = new ArrayList<>();
		//Get the players level (world)
		
		if(level == null) return spawnerPositions; 
		
		for (int x = -range; x <= range; x++) {
			for(int y = -range; y <= range; y++) {
				for (int z = -range; z <= range; z++) {
					BlockPos currentPos = playerPos.offset(x, y, z);
					
					if(level.getBlockState(currentPos).getBlock() instanceof CustomZombieSpawnerBlock) {
						spawnerPositions.add(currentPos);
					}
				}
			}
		}
		
		return spawnerPositions;
	}
	
	
	public static void spawnZombies(ServerLevel level, BlockPos playerPos, List<BlockPos> spawners) {
	
		int attempts = 0;
		int zombiesSpawned = 0;
		
		while(zombiesSpawned < WaveManager.zombiesRemaining && activeZombies.size() < maxZombiesOnField && !spawners.isEmpty() && attempts < 100) {
			attempts++;
			BlockPos spawnerPos = spawners.get(random.nextInt(spawners.size()));
			CustomZombie zombie = createZombie(level);
			
			if(zombie != null) {
				zombie.setPos(spawnerPos.getX(), spawnerPos.getY(), spawnerPos.getZ());
				level.addFreshEntity(zombie);
				activeZombies.add(zombie);
				zombiesSpawned++;
				
			}
		}
	}

	public static void spawnZombiesBetween(BlockPos spawner1, BlockPos spawner2) {
		//spawn logic between the two spawners
	}
	
	public static CustomZombie createZombie(ServerLevel level) {
		
		for(ZombieTypes type : ZombieTypes.values()) {
			if (currentRound >= type.getSpawnInterval() && currentRound % type.getSpawnInterval() == 0) {
				try {
					Mob mob = type.getEntityType().create(level);
					if(mob instanceof CustomZombie zombie) {
						return zombie;
					}
				} catch (Exception e) {
					e.printStackTrace();
					}
				}
			}
		
		return EntityInit.CZOMBIE.get().create(level);
		
	}
}