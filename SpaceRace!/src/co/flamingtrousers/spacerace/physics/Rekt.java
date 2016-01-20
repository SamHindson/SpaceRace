package co.flamingtrousers.spacerace.physics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import co.flamingtrousers.spacerace.universe.UniverseColors;

public class Rekt {
	private double x, y, w, h;
	private double angle;

	private double d, a, r;

	private Vec2[] vertices;

	public Rekt(Simulation simulation, double x, double y, double w, double h) {
		this.x = x;
		this.y = y;

		this.w = w;
		this.h = h;

		d = Vec2.mag(w / 2, h / 2);
		a = Math.atan2(h / 2, w / 2);
		System.out.println(d);

		angle = Math.random() * 6.28;
		r = Math.random();

		vertices = new Vec2[4];
		for (int j = 0; j < 4; j++) {
			vertices[j] = new Vec2(0, 0);
		}
		
		simulation.add(this);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(UniverseColors.GREY.brighter());
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y);
		transform.rotate(angle);
		g2d.setTransform(transform);
		g2d.drawRect((int) (-w / 2.), (int) (-h / 2.), (int) w, (int) h);
		transform.setToIdentity();
		g2d.setTransform(transform);

		g2d.setColor(UniverseColors.ORANGE);

		for (Vec2 vec : vertices) {
			g2d.fillOval((int) vec.x - 3, (int) vec.y - 3, (int) 7, (int) 7);
		}
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void update(double dt) {
		// x += dx * dt;

		angle += dt * 3. * r;
		// angle = 0;

		vertices[0].set(x + d * Math.cos(-angle + a), y - d * Math.sin(-angle + a));
		vertices[1].set(x + d * Math.cos(angle + a), y + d * Math.sin(angle + a));
		vertices[2].set(x - d * Math.cos(angle + a), y - d * Math.sin(angle + a));
		vertices[3].set(x - d * Math.cos(-angle + a), y + d * Math.sin(-angle + a));
	}

	public Vec2 getVert(int v) {
		return vertices[v];
	}
}
