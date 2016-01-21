package co.flamingtrousers.spacerace.universe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import co.flamingtrousers.spacerace.physics.GravityMan;

public class Planet extends GravityMan {
	private double radius;
	
	private Image sprite;
	private Color color, speckleColor;
	
	private double orbitAngle;
	private double x, y;
	
	private Random specklesGenerator;
	private int speckleCount;
	
	public Planet(int planetNumber, double angle) {
		color = UniverseColors.getRandom();
		radius = Math.random() * 1500 + 200;
		mass = 10 * Math.PI * radius * radius;
		x = y = 0;
		
		specklesGenerator = new Random(planetNumber + (int)(mass) << 2);
		speckleCount = 5000;
		
		speckleColor = color.darker();
		
		System.out.println(radius);
		
		sprite = new BufferedImage((int)radius * 2, (int)radius * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = sprite.getGraphics();
		graphics.setColor(color);
		graphics.fillOval(0, 0, (int)radius * 2, (int)radius * 2);
		graphics.setColor(speckleColor);
		
		for(int h = 0; h < speckleCount; h++) {
			int sx = (int)(specklesGenerator.nextInt((int)radius * 4));
			int sy = (int)(specklesGenerator.nextInt((int)radius * 4));
			
			if(specklesGenerator.nextBoolean()) {
				speckleColor = new Color(color.darker().getRed(), color.darker().getGreen(), color.darker().getBlue(), 12);
			} else {
				speckleColor = new Color(color.brighter().getRed(), color.brighter().getGreen(), color.brighter().getBlue(), 12);
			}
			
			graphics.setColor(speckleColor);
			
			int dx = (int)radius * 1 - sx;
			int dy = (int)radius * 1 - sy;
			
			double d = Math.sqrt(dx * dx + dy * dy);
			
			if(d > (radius*9.75f) || d < 5) {
				h--;
				continue;
			}
			
			int size = (int)(radius*1. / d*1.) + 1;
			
			if(size > radius * 0.5) {
				h--;
				continue;
			}
			
			graphics.fillRect(sx - size / 2, sy - size / 2, size * 10, size * 10);
			//graphics.fillRect(sx, sy, 1, 1);
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(sprite, (int)(getX() - radius / 2.), (int)(getY() - radius / 2.), (int)radius, (int)radius, null);
		g2d.setColor(UniverseColors.RED);
	}
	
	public double getRadius() {
		return radius / 2.;
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
	public double getMass() {
		return mass;
	}
	
	@Override
	public double getInfluenceSphere() {
		return radius * 10;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
}
