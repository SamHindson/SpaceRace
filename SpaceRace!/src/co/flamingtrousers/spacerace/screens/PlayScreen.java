package co.flamingtrousers.spacerace.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.graphics.Camera;
import co.flamingtrousers.spacerace.universe.Universe;
import co.flamingtrousers.spacerace.universe.UniverseColors;

public class PlayScreen implements Screen {

	private Camera camera;
	private Universe universe;
	
	@Override
	public void load() {
		universe = new Universe();
		camera = new Camera();
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(0.1f, 0.1f, 0.1f));
		g2d.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());
		
		camera.refreshGraphics(g2d);
		universe.draw(g2d);
		
		camera.reset(g2d);
		
		g2d.setColor(UniverseColors.WHITE);
		g2d.drawString("SpaceRace!", 10, 10);
	}

	@Override
	public void update(double dt) {
		camera.update(dt);
	}

	@Override
	public void unload() {

	}

}
