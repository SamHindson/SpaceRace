package co.flamingtrousers.spacerace.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.graphics.Flames;
import co.flamingtrousers.spacerace.graphics.Sprite;
import co.flamingtrousers.spacerace.input.Input;

public class ScreenManager {
	private static Screen currentScreen;
	
	public static void init() {
		Sprite.init();
		Input.init();
		currentScreen = new PlayScreen();
		currentScreen.load();
	}
	
	public static void update(double dt) {
		currentScreen.update(dt);
	}
	
	public static void render(Graphics2D g2d) {
		currentScreen.render(g2d);
		
		/*if(Game.DEBUG) {
			g2d.setColor(new Color(0, 255, 255, 50));
			
			int divisionWidth = Game.getScreenWidth() / 20;
			for(int p = 0; p < 20; p++) {
				g2d.drawLine(divisionWidth * p, 0, divisionWidth * p, Game.getScreenHeight());
				g2d.drawLine(0, divisionWidth * p, Game.getScreenWidth(), divisionWidth * p);
			}
			
			g2d.setColor(new Color(255, 122, 122, 75));
			g2d.drawLine(0, Game.getScreenHeight() / 2, Game.getScreenWidth(), Game.getScreenHeight() / 2);
			g2d.drawLine(Game.getScreenWidth() / 2, 0, Game.getScreenWidth() / 2, Game.getScreenHeight());
		}*/
	}
}
