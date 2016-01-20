package co.flamingtrousers.spacerace.screens;

import java.awt.Graphics2D;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.graphics.Explosion;
import co.flamingtrousers.spacerace.graphics.Flames;
import co.flamingtrousers.spacerace.universe.UniverseColors;

public class TestScreen implements Screen {
	
	Explosion e;
	
	@Override
	public void load() {
		e = new Explosion(100, 100, 300);
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(UniverseColors.GREY.darker());
		g2d.fillRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight());
		e.draw(g2d);
	}

	@Override
	public void update(double dt) {
		e.update(dt);
	}

	@Override
	public void unload() {

	}

}
