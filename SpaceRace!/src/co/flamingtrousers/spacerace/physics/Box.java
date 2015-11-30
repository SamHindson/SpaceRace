package co.flamingtrousers.spacerace.physics;

import java.awt.Graphics2D;

import co.flamingtrousers.spacerace.universe.UniverseColors;

public class Box extends Mass {
	public Box(double x, double y) {
		this.x = x;
		this.y = y;
		
		dy = -40;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(UniverseColors.BLUE);
		g2d.fillRect((int)(x), (int)(y), 1, 1);
	}

	@Override
	protected double getHeight() {
		return 1;
	}

	@Override
	public double getFocusX() {
		return x;
	}

	@Override
	public double getFocusY() {
		return y;
	}
	
	@Override
	public boolean isMoving() {
		return true;
	}
}
