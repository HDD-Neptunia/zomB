package waves;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaveManager {
  private static int currentWave = 1;
  private static int maxZombiesPerWave = 7;
  private static int zombiesSpawned = 0;
  private static boolean waveInProgress = false;
  
  public static void startWaves() {
	  waveInProgress = true;
	  currentWave = 1;
	  maxZombiesPerWave = 7;
	  spawnWave();
  }
  
  public static void spawnWave() {
	  if (!waveInProgress) return;
	  
	  zombiesSpawned = 0;
	  for(int i = 0; i < maxZombiesPerWave; i++) {
		  spawnZombie();
	  }
  }
	  
  public static void spawnZombie() {
	  
  }
	
	  
  public static void onZombieDefeated() {
	  zombiesSpawned--;
	  
	  if (zombiesSpawned <= 0) {
		  currentWave++;
		  if(currentWave % 3 == 0)
			  maxZombiesPerWave += 2;
		  scheduleNextWave();
	  }
  }
  
  private static void scheduleNextWave() {
	  //delay
	  Executors.newScheduledThreadPool(1).schedule(() -> spawnWave(), 5, TimeUnit.SECONDS);
	  
  }
  
  public static void stopWaves() {
	  waveInProgress = false;
  }
  }

