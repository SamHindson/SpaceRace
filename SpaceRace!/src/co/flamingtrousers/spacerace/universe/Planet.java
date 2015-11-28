package co.flamingtrousers.spacerace.universe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;

public class Planet {
	private double radius;
	private double mass;
	
	private Image sprite;
	private Color color, speckleColor;
	
	private double orbitAngle;
	private double x, y;
	
	private Random specklesGenerator;
	private int speckleCount;
	
	public Planet(int planetNumber, double angle) {
		color = UniverseColors.getRandom();
		radius = Math.random() * 100 + 50;
		mass = 10 * Math.PI * radius * radius;
		x = y = 0;
		
		specklesGenerator = new Random(planetNumber + (int)(mass) << 2);
		speckleCount = 1000;
		
		speckleColor = color.darker();
		
		sprite = new BufferedImage((int)radius * 2, (int)radius * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = sprite.getGraphics();
		graphics.setColor(color);
		graphics.fillOval(0, 0, (int)radius * 2, (int)radius * 2);
		graphics.setColor(speckleColor);
		
		for(int h = 0; h < speckleCount; h++) {
			int sx = (int)(specklesGenerator.nextInt((int)radius * 2));
			int sy = (int)(specklesGenerator.nextInt((int)radius * 2));
			
			int dx = (int)radius - sx;
			int dy = (int)radius - sy;
			
			double d = Math.sqrt(dx * dx + dy * dy);
			
			if(d > (radius*0.9) || d < (radius * 1/8)) {
				h--;
				continue;
			}
			
			graphics.fillRect(sx, sy, (int)(100/d), (int)(100/d));
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(sprite, (int)x, (int)y, (int)radius, (int)radius, null);
	}
}
