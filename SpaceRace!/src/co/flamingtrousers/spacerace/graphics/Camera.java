package co.flamingtrousers.spacerace.graphics;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import co.flamingtrousers.spacerace.Game;
import co.flamingtrousers.spacerace.input.Input;

public class Camera {
	public double x, y, fx, fy;
	public double zoom = 0.2;

	public void update(double dt) {
		if (Game.DEBUG) {
			if (Input.getKey(KeyEvent.VK_NUMPAD8)) {
				y += 500 * dt;
			} else if (Input.getKey(KeyEvent.VK_NUMPAD2)) {
				y -= 500 * dt;
			}

			if (Input.getKey(KeyEvent.VK_NUMPAD4)) {
				x += 500 * dt;
			} else if (Input.getKey(KeyEvent.VK_NUMPAD6)) {
				x -= 500 * dt;
			}

			if (Input.getButton(MouseEvent.BUTTON1)) {
				zoom += dt * 10;
			} else if (Input.getButton(MouseEvent.BUTTON3)) {
				zoom -= dt * 10;
			}

			/*
			 * if((int)Game.elapsedTime % 2 == 0) { zoom += dt; } else { zoom -=
			 * dt; }
			 */
		}
	}

	public void refreshGraphics(Graphics2D g2d) {
		g2d.scale(getZoom(), getZoom());
		g2d.translate(x, y);
	}

	public void reset(Graphics2D g2d) {
		g2d.translate(-x, -y);
		g2d.scale(zoom, zoom);
	}

	public void lookAt(Focusable focusable) {
		lookAt(focusable.getFocusX(), focusable.getFocusY());
	}

	public void lookAt(double xx, double yy) {
		fx = -xx;
		fy = -yy;
		x = fx + Game.getScreenWidth() / (2 * getZoom());
		y = fy + Game.getScreenHeight() / (2 * getZoom());
	}

	private double getZoom() {
		return 1. / zoom;
	}

}
