//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package bulk;

public class WaveManager {
	
	private static int roundNumber;
	public static int currentRound = roundNumber;
	public static int zombiesRemaining;
	

	public static void startWave() {
		roundNumber++;
	}
	
	private void calculateZombiesForWave(int roundNumber) {
		zombiesRemaining = roundNumber*4; 
	}   
}
