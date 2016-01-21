package co.flamingtrousers.spacerace.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import co.flamingtrousers.spacerace.universe.UniverseColors;

public class Explosion {
	private boolean active = true;
	private double x, y;
	private ExplosionSpeck[] specks;

	private Polygon bam;
	private int bamfames = 30, bams = 0;

	public Explosion(int size, double x, double y) {
		specks = new ExplosionSpeck[size];

		this.x = x;
		this.y = y;

		for (int k = 0; k < size; k++) {
			specks[k] = new ExplosionSpeck(this, 0.3);
		}

		bam = new Polygon();
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

		if (!alive)
			active = false;
		else {

			if (bams < bamfames) {
				bams++;
				int k = (int) (Math.random() * 150) + 20;
				bam.npoints = k;

				int[] xx = new int[k];
				int[] yy = new int[k];

				for (int i = 0; i < k; i++) {
					int r = (int) ((100 + Math.random() * 75) * ((bamfames * 1. - bams * 1.) / bamfames * 1.));
					double a = (Math.PI * 2) * (i * 1. / k * 1.);
					xx[i] = (int) (x + r * Math.cos(a));
					yy[i] = (int) (y + r * Math.sin(a));
				}

				bam.xpoints = xx;
				bam.ypoints = yy;
			}
		}
	}

	public void draw(Graphics2D g2d) {
		for (ExplosionSpeck speck : specks) {
			if (speck != null) {
				speck.draw(g2d);
			}
		}

		if (bams < bamfames) {
			g2d.setColor(new Color(255, 255, 255, 50));
			g2d.fill(bam);
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
			g2d.setColor(age < lifetime * 0.05 ? Color.WHITE
					: age < lifetime * 0.2 ? UniverseColors.ORANGE : UniverseColors.RED);
			g2d.fillRect((int) x, (int) y, 1, 1);
		}
	}
}