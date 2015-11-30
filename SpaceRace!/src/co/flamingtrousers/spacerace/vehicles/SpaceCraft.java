package co.flamingtrousers.spacerace.vehicles;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import co.flamingtrousers.spacerace.graphics.Sprite;
import co.flamingtrousers.spacerace.physics.Mass;

public abstract class SpaceCraft extends Mass {
	protected Sprite sprite;
	protected AffineTransform identity, affine;
	protected double angle;
	protected double thrust;
	
	public SpaceCraft(double x, double y) {
		this.x = x;
		this.y = y;
		
		identity = new AffineTransform();
	}
	
	public abstract void draw(Graphics2D g2d);
}
