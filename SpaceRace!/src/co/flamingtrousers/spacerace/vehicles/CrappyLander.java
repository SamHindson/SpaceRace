package co.flamingtrousers.spacerace.vehicles;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import co.flamingtrousers.spacerace.graphics.Flames;
import co.flamingtrousers.spacerace.graphics.Sprite;
import co.flamingtrousers.spacerace.input.Input;
import co.flamingtrousers.spacerace.universe.Universe;

public class CrappyLander extends SpaceCraft {
	
	private Flames flames;
	
	public CrappyLander(double x, double y) {
		super(x, y);
		sprite = new Sprite(x, y, "crappylander");
		sprite.setScale(100, 100);
		System.out.println(x + " " + y);
		
		flames = new Flames(100);
	}
	
	@Override
	public void update(Universe universe, double dt) {
		if(Input.getKey(KeyEvent.VK_SPACE)) {
			thrust = 40.f;
			flames.setActive(true);
		} else {
			thrust = 0;
			flames.setActive(false);
		}
		
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
		
		flames.set(x, y, angle);
		flames.update(dt);
	}
	
	@Override
	public double getFocusX() {
		return x + 1.5;
	}
	
	@Override
	public double getFocusY() {
		return y + 2;
	}

	@Override
	protected double getHeight() {
		return 80;
	}
	
	@Override
	public boolean isMoving() {
		return thrust != 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		flames.draw(g2d);
		
		affine = new AffineTransform();
		affine.setToRotation(angle, x + 1.5, y + 2);
		affine.translate(x, y);
		affine.scale(2, 2);
		sprite.draw(g2d, affine);
	}

}
