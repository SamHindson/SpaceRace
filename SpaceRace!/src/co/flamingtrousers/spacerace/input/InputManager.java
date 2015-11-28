package co.flamingtrousers.spacerace.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

	@Override
	public void keyPressed(KeyEvent e) {
		Input.setKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Input.setKey(e.getKeyCode(), false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Input.setButton(e.getButton(), true);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Input.setButton(e.getButton(), false);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Input.setMousePosition(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}

}
