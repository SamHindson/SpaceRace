package co.flamingtrousers.spacerace.physics;

import java.awt.event.KeyEvent;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.graphics.Focusable;
import co.flamingtrousers.spacerace.input.Input;
import co.flamingtrousers.spacerace.universe.Universe;

public abstract class Mass implements Focusable {
	protected double x, y, dx, dy;
	protected boolean onSurface;

	public void pull(GravityMan puller, double dt) {
		double dxx = puller.getX() - x;
		double dyy = puller.getY() - y;
		double angle = Math.atan2(dyy, dxx);

		double distance = Math.sqrt(Math.pow(dxx, 2) + Math.pow(dyy, 2));

		if (distance > puller.getInfluenceSphere()) {
			return;
		}

		double mass = puller.getMass();
		double force = Game.GRAV * mass / (distance * distance);

		if (distance <= puller.getRadius() + getHeight() / 2) {
			x = puller.getX() + (puller.getRadius() + getHeight() / 2) * -Math.cos(angle);
			y = puller.getY() + (puller.getRadius() + getHeight() / 2) * -Math.sin(angle);
			
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
			pull(man, dt);
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

	protected abstract double getHeight();

	public abstract boolean isMoving();
}
