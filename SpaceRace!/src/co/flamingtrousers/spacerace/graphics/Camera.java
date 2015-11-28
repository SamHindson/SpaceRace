package co.flamingtrousers.spacerace.graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.input.Input;

public class Camera {
	public double x, y, dx, dy;
	public double zoom = 1;

	public void update(double dt) {
		if(Game.DEBUG) {
			if(Input.getKey(KeyEvent.VK_NUMPAD8)) {
				dy = -500;
			} else if(Input.getKey(KeyEvent.VK_NUMPAD2)) {
				dy = 500;
			} else {
				dy = 0;
			}
			
			if(Input.getKey(KeyEvent.VK_NUMPAD4)) {
				dx = -500;
			} else if(Input.getKey(KeyEvent.VK_NUMPAD6)) {
				dx = 500;
			} else {
				dx = 0;
			}
			
			if(Input.getButton(MouseEvent.BUTTON1)) {
				zoom += dt;
			} else if(Input.getButton(MouseEvent.BUTTON3)) {
				zoom -= dt;
			}
			
			/*if((int)Game.elapsedTime % 2 == 0) {
				zoom += dt;
			} else {
				zoom -= dt;
			}*/
			
			x += dx * dt;
			y += dy * dt;
		}
	}
	
	public void refreshGraphics(Graphics2D g2d) {
		g2d.scale(zoom, zoom);
		g2d.translate(x, y);
	}

	public void reset(Graphics2D g2d) {
		g2d.translate(-x, -y);
		g2d.scale(1 / zoom, 1 / zoom);
	}
	
}
