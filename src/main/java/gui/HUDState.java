package gui;

public class HUDState {
	private static boolean enabled;
	
	public static boolean isEnabled() {
		return enabled;
	}
	
	public static void setEnabled(boolean value) {
		enabled = value;
	}
}
