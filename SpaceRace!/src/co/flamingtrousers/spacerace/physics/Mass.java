package co.flamingtrousers.spacerace.physics;

import java.awt.event.KeyEvent;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.graphics.Focusable;
import co.flamingtrousers.spacerace.input.Input;
import co.flamingtrousers.spacerace.universe.Universe;

public abstract class Mass implements Focusable {
	protected double x, y, dx, dy;
	protected boolean onSurface;

	public void pull(GravityMan puller, Universe u, double dt) {
		double dxx = puller.getX() - getX();
		double dyy = puller.getY() - getY();
		double angle = Math.atan2(dyy, dxx);

		double distance = Math.sqrt(Math.pow(dxx, 2) + Math.pow(dyy, 2));

		if (distance > puller.getInfluenceSphere()) {
			return;
		}

		double mass = puller.getMass();
		double force = Game.GRAV * mass / (distance * distance);

		if (distance <= puller.getRadius() + getHeight() / 4) {
			
			if(Vec2.mag(dx, dy) > getMaxImpactThreshhold()) {
				handleHighImpactCollision(u);
			}
			
			setX(puller.getX() + (puller.getRadius() + getHeight() / 4) * -Math.cos(angle));
			setY(puller.getY() + (puller.getRadius() + getHeight() / 4) * -Math.sin(angle));
			
			onSurface = true;

			if(!isMoving())
				dx = dy = 0;

			return;
		} else {
			onSurface = false;
		}

		double ax = force * Math.cos(angle) / 3;
		double ay = force * Math.sin(angle) / 3;

		dx += ax * dt;
		dy += ay * dt;
	}


	public void update(Universe universe, double dt) {
		for (GravityMan man : universe.getGravityMen()) {
			pull(man, universe, dt);
		}

		x += dx * dt;
		y += dy * dt;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	protected abstract double getHeight();
	public abstract boolean isMoving();
	
	protected abstract void handleHighImpactCollision(Universe u);
	protected abstract double getMaxImpactThreshhold();
}
