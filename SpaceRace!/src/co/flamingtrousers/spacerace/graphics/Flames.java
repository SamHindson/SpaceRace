package co.flamingtrousers.spacerace.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import co.flamingtrousers.spacerace.universe.UniverseColors;

// TODO my god fix this shit up

public class Flames {
	Speck[] specks;
	double x, y, angle, width = 20;
	boolean active;

	int lastEmitted = 0, emitted = 0;
	double delay = 0.005;
	double time;

	public Flames(int number) {
		specks = new Speck[number];

		for (int p = 0; p < number; p++) {
			specks[p] = new Speck(this);
		}
	}

	public void setActive(boolean active) {
		if (!this.active && active) {
			this.active = true;
		} else if (!active) {
			this.active = false;
		}
	}

	public void update(double dt) {
		if (active) {
			time += dt;
			
			delay = 0.0001;
			
			width = 10;

			if (time > delay) {
				specks[lastEmitted].reinitialize();
				lastEmitted++;

				if (lastEmitted > specks.length - 1) {
					lastEmitted = 0;
				}

				time = 0;
			}
		}

		for (int p = 0; p < specks.length; p++) {
			specks[p].update(dt);
		}
	}

	public void draw(Graphics2D g2d) {
		for (int p = 0; p < specks.length; p++) {
			specks[p].draw(g2d);
		}
	}

	public void set(double x2, double y2, double angle2) {
		x = x2;
		y = y2;
		this.angle = angle2;
	}
}

class Speck {
	Flames parent;
	double x, y, dx, dy, lifetime = 1, age;
	Color c;
	boolean dead = true;
	
	int size;

	Speck(Flames parent) {
		this.parent = parent;
	}

	void reinitialize() {
		double angle = parent.angle + (Math.random() * 0.2 - 0.1);
		double v = 500;
		x = parent.x + (int)((Math.random() * parent.width) - parent.width / 2) * Math.sin(angle);
		y = parent.y + (int)((Math.random() * parent.width) - parent.width / 2) * Math.cos(angle);
		dx = v * Math.cos(angle);
		dy = v * Math.sin(angle);
		age = 0;
		
		size = (int)(Math.random() * 3) + 2;

		c = Math.random() < 0.25 ? UniverseColors.DARKORANGE : Math.random() < 0.5 ? UniverseColors.ORANGE : UniverseColors.RED;
		lifetime = 0.2 * Math.random();

		dead = false;
	}

	void update(double dt) {
		if (!dead) {
			x += dx * dt;
			y += dy * dt;

			age += dt;

			if (age > lifetime) {
				dead = true;
			}
		}
	}

	void draw(Graphics2D g2d) {
		if (!dead) {
			//System.out.println(12 + 46);
			g2d.setColor(c);
			g2d.fillRect((int)x, (int)y, size, size);
		}
	}
}
