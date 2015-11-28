package co.flamingtrousers.spacerace.universe;

import java.awt.Graphics2D;

public class Universe {
	private Star star;
	private Planet[] planets;
	
	private Planet testPlanet;
	
	public Universe() {
		testPlanet = new Planet(0, 0);
	}
	
	public void update(double dt) {
		
	}
	
	public void draw(Graphics2D g2d) {
		testPlanet.draw(g2d);
	}
}
