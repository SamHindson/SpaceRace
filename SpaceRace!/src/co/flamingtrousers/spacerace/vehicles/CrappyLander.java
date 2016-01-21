package co.flamingtrousers.spacerace.vehicles;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import co.flamingtrousers.spacerace.graphics.Flames;
import co.flamingtrousers.spacerace.graphics.Sprite;
import co.flamingtrousers.spacerace.input.Input;
import co.flamingtrousers.spacerace.physics.Rekt;
import co.flamingtrousers.spacerace.universe.Universe;

public class CrappyLander extends SpaceCraft {
	
	private Flames flames;
	
	private Rekt collider;
	
	public CrappyLander(Universe u, double x, double y) {
		super(x, y);
		sprite = new Sprite(x, y, "crappylander");
		sprite.setScale(100, 100);
		System.out.println(x + " " + y);
		
		collider = new Rekt(u.getPhysics(), x, y, 40, 80);
		
		flames = new Flames(100);
		
		fuel = 1;
	}
	
	@Override
	public void update(Universe universe, double dt) {
		if(Input.getKey(KeyEvent.VK_SPACE) && fuel > 0) {
			thrust = 80.f;
			flames.setActive(true);
			fuel -= dt / 5;
		} else {
			thrust = 0;
			flames.setActive(false);
		}
		
		double fx = x + 15 + 20 * Math     .sin(-angle);
		double fy = y + 15 + 20 * Math.cos(-angle);
		flames.set(fx, fy, angle + Math.PI / 2.);
		
		if(!onSurface) 
			if(Input.getKey(KeyEvent.VK_A))
				angle -= dt;
			else if(Input.getKey(KeyEvent.VK_D))
				angle += dt;
		
		dx += thrust * Math.sin(angle) * dt;
		dy += -thrust * Math.cos(angle) * dt;
		
		super.update(universe, dt);
		
		//sprite.setPosition(x, y);
		sprite.setScale(1, 1);
		
		collider.setX(getX());
		collider.setY(getY());
		
		flames.update(dt);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		flames.draw(g2d);
		
		affine = new AffineTransform();
		affine.setToRotation(angle, x + 15, y + 20);
		affine.translate(x, y);
		affine.scale(2, 2);
		sprite.draw(g2d, affine);
	}
	
	

	@Override
	public double getX() {
		return x + 15.5;
	}
	
	@Override
	public double getY() {
		return y + 20.;
	}
	
	@Override
	public void setX(double x) {
		this.x = x - 15.5;
	}
	
	@Override
	public void setY(double y) {
		this.y = y - 20.;
	}
	
	@Override
	protected double getMaxImpactThreshhold() {
		return 1;
	}
	
	@Override
	protected void handleHighImpactCollision(Universe u) {
		u.addExplosion(getX(), getY());
	}
	
	@Override
	public double getFocusX() {
		return getX();
	}
	
	@Override
	public double getFocusY() {
		return getY();
	}

	@Override
	protected double getHeight() {
		return 80;
	}
	
	@Override
	public boolean isMoving() {
		return thrust != 0;
	}

}
