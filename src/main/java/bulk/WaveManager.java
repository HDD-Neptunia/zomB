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
