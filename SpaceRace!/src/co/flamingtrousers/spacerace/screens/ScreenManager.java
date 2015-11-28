package co.flamingtrousers.spacerace.screens;

import java.awt.Graphics2D;

public class ScreenManager {
	private static Screen currentScreen;
	
	public static void init() {
		currentScreen = new PlayScreen();
		currentScreen.load();
	}
	
	public static void update(double dt) {
		currentScreen.update(dt);
	}
	
	public static void render(Graphics2D g2d) {
		currentScreen.render(g2d);
	}
}
