package co.flamingtrousers.spacerace.physics;

import java.awt.Graphics2D;

public class AABB implements Body {
	private Vec2 min, max;
	private Vec2 velocity;
	
	private double restitution;

	public AABB(double x, double y, double w, double h, double dx, double dy) {
		min = new Vec2(x, y);
		max = new Vec2(w + x, h + y);

		velocity = new Vec2(dx, dy);
		
		restitution = Math.random();
	}

	public void update(double dt) {
		min.x += velocity.x * dt;
		max.x += velocity.x * dt;
		min.y += velocity.y * dt;
		max.y += velocity.y * dt;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawRect((int) min.x, (int) min.y, (int) (max.x - min.x), (int) (max.y - min.y));
	}

	public boolean intersects(AABB what) {
		if (max.x < what.min.x || min.x > what.max.x)
			return false;
		if (max.y < what.min.y || min.y > what.max.y)
			return false;

		return true;
	}
	
	public Vec2 getCenter() {
		return new Vec2((max.x + min.x) / 2., (max.y + min.y) / 2.);
	}

	public double getRestitution() {
		return 0.9;
	}
	
	public Vec2 getVelocity() {
		return velocity;
	}

	public double getMass() {
		return 10;
	}

	public void editVelocity(Vec2 v) {
		velocity = velocity.add(v);
	}

	public void setVelocity(Vec2 z) {
		velocity = z;
	}
}
