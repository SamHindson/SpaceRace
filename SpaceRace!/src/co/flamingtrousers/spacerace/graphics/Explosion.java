package co.flamingtrousers.spacerace.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import co.flamingtrousers.spacerace.universe.UniverseColors;

public class Explosion {
	private boolean active = true;
	private double x, y;
	private ExplosionSpeck[] specks;

	public Explosion(int size, double x, double y) {
		specks = new ExplosionSpeck[size];

		this.x = x;
		this.y = y;

		for (int k = 0; k < size; k++) {
			specks[k] = new ExplosionSpeck(this, 0.3);
		}
	}
	
	public boolean isActive() {
		return active;
	}

	public void update(double dt) {
		boolean alive = false;
		for (int u = 0; u < specks.length; u++) {
			if (specks[u] != null) {
				if (specks[u].dead) {
					specks[u] = null;
					continue;
				}
				specks[u].update(dt);
				alive = true;
			}
		}
		
		if(!alive)
			active = false;
	}

	public void draw(Graphics2D g2d) {
		for (ExplosionSpeck speck : specks) {
			if (speck != null) {
				speck.draw(g2d);
			}
		}
	}

	private class ExplosionSpeck {
		private boolean dead = false;
		private Explosion parent;
		private double age, lifetime;
		private double x, y, dx, dy;
		private double damping = 0.99;

		public ExplosionSpeck(Explosion parent, double magnitude) {
			this.parent = parent;
			age = 0;
			lifetime = 5 * Math.random();

			x = parent.x;
			y = parent.y;

			double angle = Math.random() * 6.28;
			double variance = Math.random();

			dx = 3000 * magnitude * Math.cos(angle) * variance;
			dy = 3000 * magnitude * Math.sin(angle) * variance;
		}

		public void update(double dt) {
			age += dt;

			if (age > lifetime) {
				dead = true;
			}

			dx *= damping;
			dy *= damping;

			x += dx * dt;
			y += dy * dt;
		}

		public void draw(Graphics2D g2d) {
			g2d.setColor(age < lifetime * 0.05 ? Color.WHITE : age < lifetime * 0.2 ? UniverseColors.ORANGE : UniverseColors.RED);
			g2d.fillRect((int) x, (int) y, 5, 5);
		}
	}
}