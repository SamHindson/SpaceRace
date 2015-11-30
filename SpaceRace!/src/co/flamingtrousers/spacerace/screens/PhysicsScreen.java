package co.flamingtrousers.spacerace.screens;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.physics.AABB;
import co.flamingtrousers.spacerace.physics.Simulation;
import co.flamingtrousers.spacerace.universe.UniverseColors;

public class PhysicsScreen implements Screen {
	
	Simulation sim;
	Stroke storke;
	
	boolean wow = false;
	
	@Override
	public void load() {
		sim = new Simulation();
		sim.add(new AABB(10, 100, 20, 20, 100, 0));
		sim.add(new AABB(500, 100, 20, 20, -100, 0));
		storke = new BasicStroke(2.f);
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(wow ? UniverseColors.RED : UniverseColors.GREY.darker());
		g2d.fillRect(0, 0, Game.getScreenWidth(), Game.getScreenHeight());
		g2d.setColor(UniverseColors.GREY.brighter());
		g2d.setStroke(storke);
		
		sim.render(g2d);
	}

	@Override
	public void update(double dt) {
		sim.update(dt);
	}

	@Override
	public void unload() {

	}

}
