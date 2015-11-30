package co.flamingtrousers.spacerace.physics;

import co.flamingtrousers.spacerace.graphics.Focusable;

public abstract class GravityMan implements Focusable {
	protected double x, y, mass;
	public abstract double getX();
	public abstract double getY();
	public abstract double getMass();
	
	public abstract double getInfluenceSphere();
	public abstract double getRadius();
}
