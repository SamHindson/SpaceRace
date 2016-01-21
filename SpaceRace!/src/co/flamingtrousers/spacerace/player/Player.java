package co.flamingtrousers.spacerace.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.input.Input;
import co.flamingtrousers.spacerace.physics.GravityMan;
import co.flamingtrousers.spacerace.physics.Mass;
import co.flamingtrousers.spacerace.physics.Vec2;
import co.flamingtrousers.spacerace.universe.Planet;
import co.flamingtrousers.spacerace.universe.Universe;
import co.flamingtrousers.spacerace.vehicles.SpaceCraft;
import co.flamingtrousers.spacerace.weapons.Bomb;

public class Player extends Mass {
	private SpaceCraft ride;

	private Planet environment;

	private double angleAroundEnvironment;
	private boolean moving, justThrown;

	public Player(Planet environment, double x, double y) {
		this.environment = environment;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect((int) (x - 0.5f), (int) (y - 1), 1, 2);
	}

	@Override
	public void update(Universe universe, double dt) {
		angleAroundEnvironment = Math.atan2(y - environment.getY(), x - environment.getX());

		double dxx = environment.getX() - getX();
		double dyy = environment.getY() - getY();

		double distance = Math.sqrt(Math.pow(dxx, 2) + Math.pow(dyy, 2));

		if (distance <= environment.getRadius() + getHeight() / 4) {
			onSurface = true;
		} else {
			onSurface = false;
		}

		if (Input.getKey(KeyEvent.VK_ESCAPE)) {
			x = 0;
			y = -environment.getRadius() - 5;
		}

		if (Input.getKey(KeyEvent.VK_G)) {
			if (!justThrown) {
				new Bomb(universe, x, y);
				justThrown = true;
			}
		} else {
			justThrown = false;
		}

		if (onSurface) {
			moving = true;

			if (Input.getKey(KeyEvent.VK_SPACE)) {
				dx += 20 * Math.cos(angleAroundEnvironment);
				dy += 20 * Math.sin(angleAroundEnvironment);

				onSurface = false;

				x += dx * dt;
				y += dy * dt;

				return;
			}

			if (Input.getKey(KeyEvent.VK_D)) {
				moving = true;
				dx = 50 * Math.sin(-angleAroundEnvironment);
				dy = 50 * Math.cos(-angleAroundEnvironment);
			} else if (Input.getKey(KeyEvent.VK_A)) {
				moving = true;
				dx = -50 * Math.sin(-angleAroundEnvironment);
				dy = -50 * Math.cos(-angleAroundEnvironment);
			} else {
				dx = 0;
				dy = 0;
			}
		} else {
			moving = false;
			pull(environment, universe, dt);
		}

		x += dx * dt;
		y += dy * dt;
	}

	@Override
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

			if (Vec2.mag(dx, dy) > getMaxImpactThreshhold()) {
				handleHighImpactCollision(u);
			}

			setX(puller.getX() + (puller.getRadius() + getHeight() / 4) * -Math.cos(angle));
			setY(puller.getY() + (puller.getRadius() + getHeight() / 4) * -Math.sin(angle));

			onSurface = true;

			if (!isMoving())
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

	@Override
	public double getFocusX() {
		return x;
	}

	@Override
	public double getFocusY() {
		return y;
	}

	@Override
	protected double getHeight() {
		return 2;
	}

	@Override
	public boolean isMoving() {
		return moving;
	}

	@Override
	protected void handleHighImpactCollision(Universe u) {

	}

	@Override
	protected double getMaxImpactThreshhold() {
		return 20;
	}
}
