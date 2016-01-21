package co.flamingtrousers.spacerace.universe;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import co.flamingtrousers.spacerace.graphics.Explosion;
import co.flamingtrousers.spacerace.graphics.Focusable;
import co.flamingtrousers.spacerace.physics.GravityMan;
import co.flamingtrousers.spacerace.physics.Mass;
import co.flamingtrousers.spacerace.physics.Simulation;
import co.flamingtrousers.spacerace.player.Player;
import co.flamingtrousers.spacerace.vehicles.CrappyLander;

public class Universe {
	private Star star;
	private Planet[] planets;

	private Planet testPlanet;
	
	private Player player;

	private ArrayList<Mass> masses, toAdd, toRemove;
	private ArrayList<GravityMan> gravityMen;
	private ArrayList<Explosion> explosions;
	
	private Simulation physics;

	private Focusable focus;

	private CrappyLander crappyLander;

	public Universe() {
		testPlanet = new Planet(0, 0);

		masses = new ArrayList<>();
		toAdd = new ArrayList<>();
		toRemove = new ArrayList<>();
		gravityMen = new ArrayList<>();
		explosions = new ArrayList<>();

		gravityMen.add(testPlanet);
		
		physics = new Simulation();

		//crappyLander = new CrappyLander(this, 0, testPlanet.getRadius() * -2);
		//masses.add(crappyLander);
		
		player = new Player(testPlanet, 0, -testPlanet.getRadius() - 20);
		masses.add(player);

		focus = player;
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

		focus = player;
	}

	public void draw(Graphics2D g2d) {
		//crappyLander.draw(g2d);
		testPlanet.draw(g2d);
		
		player.draw(g2d);
		
		if (!explosions.isEmpty()) {
			for (Explosion explosion : explosions) {
				explosion.draw(g2d);
			}
		}
		
		for(Mass mass : masses) {
			mass.draw(g2d);
		}
		
		physics.render(g2d);
		
		for(Mass mass : toAdd) {
			masses.add(mass);
		}
		
		toAdd.clear();
		
		for(Mass mass : toRemove) {
			masses.remove(mass);
			toRemove.remove(mass);
			break;
		}
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

	public void removeMass(Mass mass) {
		System.out.println("Need to remove a mass.");
		toRemove.add(mass);
	}

	public void addMass(Mass mass) {
		toAdd.add(mass);
	}
}
