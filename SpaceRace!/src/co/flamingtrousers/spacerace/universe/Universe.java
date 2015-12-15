package co.flamingtrousers.spacerace.universe;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import co.flamingtrousers.spacerace.graphics.Explosion;
import co.flamingtrousers.spacerace.graphics.Focusable;
import co.flamingtrousers.spacerace.physics.GravityMan;
import co.flamingtrousers.spacerace.physics.Mass;
import co.flamingtrousers.spacerace.physics.Simulation;
import co.flamingtrousers.spacerace.vehicles.CrappyLander;

public class Universe {
	private Star star;
	private Planet[] planets;

	private Planet testPlanet;

	private ArrayList<Mass> masses;
	private ArrayList<GravityMan> gravityMen;
	private ArrayList<Explosion> explosions;
	
	private Simulation physics;

	private Focusable focus;

	private CrappyLander crappyLander;

	public Universe() {
		testPlanet = new Planet(0, 0);

		masses = new ArrayList<>();
		gravityMen = new ArrayList<>();
		explosions = new ArrayList<>();

		gravityMen.add(testPlanet);
		
		physics = new Simulation();

		crappyLander = new CrappyLander(this, 0, testPlanet.getRadius() * -2);
		masses.add(crappyLander);

		focus = crappyLander;
	}

	public void update(double dt) {
		for (Mass mass : masses) {
			mass.update(this, dt);
		}
		
		physics.update(dt);

		if (!explosions.isEmpty()) {
			for (Explosion explosion : explosions) {
				explosion.update(dt);
				
				if(!explosion.isActive()) {
					explosions.remove(explosion);
					break;
				}
			}
		}

		focus = crappyLander;
	}

	public void draw(Graphics2D g2d) {
		crappyLander.draw(g2d);
		testPlanet.draw(g2d);
		
		if (!explosions.isEmpty()) {
			for (Explosion explosion : explosions) {
				explosion.draw(g2d);
			}
		}
		
		physics.render(g2d);
	}

	public Focusable getFocus() {
		return focus;
	}

	public Point getStartingPoint() {
		return new Point((int) testPlanet.getX(), (int) testPlanet.getY());
	}

	public ArrayList<GravityMan> getGravityMen() {
		return gravityMen;
	}

	public void addExplosion(double x, double y) {
		explosions.add(new Explosion(200, x, y));
	}

	public Simulation getPhysics() {
		return physics;
	}
}
