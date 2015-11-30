package co.flamingtrousers.spacerace.universe;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import co.flamingtrousers.spacerace.graphics.Focusable;
import co.flamingtrousers.spacerace.physics.Box;
import co.flamingtrousers.spacerace.physics.GravityMan;
import co.flamingtrousers.spacerace.physics.Mass;
import co.flamingtrousers.spacerace.vehicles.CrappyLander;

public class Universe {
	private Star star;
	private Planet[] planets;
	
	private Planet testPlanet;
	
	private ArrayList<Mass> masses;
	private ArrayList<GravityMan> gravityMen;
	
	private Focusable focus;
	
	private CrappyLander crappyLander;
	
	public Universe() {
		testPlanet = new Planet(0, 0);
		
		masses = new ArrayList<>();
		gravityMen = new ArrayList<>();
		
		gravityMen.add(testPlanet);
		
		crappyLander = new CrappyLander(0, testPlanet.getRadius() * -2);
		masses.add(crappyLander);
		
		focus = crappyLander;
	}
	
	public void update(double dt) {
		for(Mass mass : masses) {
			mass.update(this, dt);
		}
		
		focus = crappyLander;
	}
	
	public void draw(Graphics2D g2d) {
		testPlanet.draw(g2d);
		crappyLander.draw(g2d);
	}
	
	public Focusable getFocus() {
		return focus;
	}

	public Point getStartingPoint() {
		return new Point((int)testPlanet.getX(), (int)testPlanet.getY());
	}

	public ArrayList<GravityMan> getGravityMen() {
		return gravityMen;
	}
}
