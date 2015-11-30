package co.flamingtrousers.spacerace.physics;

public class Vec2 {
	public static final Vec2 ZERO = new Vec2(0, 0);
	
	public double x, y;

	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
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

	public Vec2 sub(Vec2 dog) {
		return new Vec2(x - dog.x, y - dog.y);
	}

	public double mag() {
		return Math.sqrt(x * x + y * y);
	}

	public double dot(Vec2 d) {
		return x * d.x + y * d.y;
	}
	
	public double cross(Vec2 c) {
		return x * c.y - y * c.x;
	}

	@Override
	public String toString() {
		return "{" + x + " | " + y + "}";
	}

	public static double dist(Vec2 one, Vec2 two) {
		return Math.sqrt(Math.pow(one.x - two.x, 2) - Math.pow(one.y - two.y, 2));
	}

	public Vec2 mul(double scalar) {
		return new Vec2(x * scalar, y * scalar);
	}

	public Vec2 add(Vec2 v) {
		return new Vec2(v.x + x, v.y + y);
	}
}
