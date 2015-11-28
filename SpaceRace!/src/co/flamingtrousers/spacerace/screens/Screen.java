package co.flamingtrousers.spacerace.screens;

import java.awt.Graphics2D;

public interface Screen {
	public void load();
	public void render(Graphics2D g2d);
	public void update(double dt);
	public void unload();
}
