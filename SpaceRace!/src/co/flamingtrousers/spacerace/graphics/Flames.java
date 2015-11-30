package co.flamingtrousers.spacerace.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import co.flamingtrousers.spacerace.universe.UniverseColors;

public class Flames {
	Speck[] specks;
	double x, y, angle;
	boolean active;

	// TODO holy hell remove this
	public static void init() {
		Speck.init();
	}

	public Flames(int number) {
		specks = new Speck[number];

		for (int p = 0; p < number; p++) {
			specks[p] = new Speck(this);
		}
	}

	public void setActive(boolean active) {
		if (!this.active && active) {
			this.active = true;
			for (Speck speck : specks) {
				speck.reinitialize();
			}
		} else if (!active) {
			this.active = false;
		}
	}

	public void update(double dt) {
		for (int p = 0; p < specks.length; p++) {
			specks[p].update(dt);
		}
	}

	public void draw(Graphics2D g2d) {
		/*
		 * for (int p = 0; p < specks.length; p++) { specks[p].draw(g2d); }
		 */

		// g2d.fillRect((int) x + 10, (int) y + 40, 10, 1);

		if (active) {
			for (int k = 0; k < 50; k++) {
				for (double p = x + 10; p < x + 20; p++) {
					if (Math.random() < (1 / (k + 0.1))) {
						g2d.setColor(new Color((int) (Math.random() * Integer.MAX_VALUE)));
						g2d.fillRect((int) p, (int) y + 40 + k, 1, 1);
					}
				}
			}
		}
	}

	public void set(double x2, double y2, double angle2) {
		x = x2;
		y = y2;
		angle = angle2;
	}
}

class Speck {
	Flames parent;
	double x, y, dx, dy, lifetime = 0.5, age;
	int color;
	boolean dead = true;

	static BufferedImage image;

	static void init() {
		image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().fillRect(0, 0, 1, 1);
	}

	Speck(Flames parent) {
		this.parent = parent;
	}

	void reinitialize() {
		double angle = parent.angle + (Math.random() * 0.5) - 0.25;
		double v = Math.random() * 10;
		x = parent.x;
		y = parent.y;
		dx = v * Math.cos(angle);
		dy = v * Math.sin(angle);
		age = 0;

		color = (int) (Math.random() * Integer.MAX_VALUE);
		lifetime = 1;

		dead = false;
	}

	void update(double dt) {
		if (!dead) {
			x += dx * dt;
			y += dy * dt;

			x += parent.x;
			y += parent.y;

			age += dt;

			if (age > lifetime) {
				dead = true;

				if (parent.active) {
					reinitialize();
				}
			}
		}
	}

	void draw(Graphics2D g2d) {
		if (!dead) {
			// System.out.println(12 + 46);
			g2d.setColor(new Color(color));
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.scale(0.1, 0.1);
			g2d.drawImage(image, (int) x, (int) y, null);
		}
	}
}
