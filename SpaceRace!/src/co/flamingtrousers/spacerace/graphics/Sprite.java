package co.flamingtrousers.spacerace.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import co.flamingtrousers.spacerace.universe.UniverseColors;

public class Sprite {
	private static HashMap<String, BufferedImage> textures;
	
	private BufferedImage image;
	private double x, y, scaleX, scaleY;
	
	public static void init() {
		textures = new HashMap<>();
		
		System.out.println("Making juice");
		
		BufferedImage errorTexture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = errorTexture.getGraphics();
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				if((x + y) % 2 == 0) {
					graphics.setColor(UniverseColors.PURPLE);
				} else {
					graphics.setColor(UniverseColors.BLUE);					
				}
				graphics.fillRect(x * 10, y * 10, 10, 10);
			}
		}
		textures.put("error", errorTexture);
		
		load("crappylander");
	}
	
	private static void load(String name) {
		BufferedImage bufferedImage;
		
		try {
			bufferedImage = ImageIO.read(new File("ass/graphics/" + name + ".png"));
			textures.put(name, bufferedImage);
			
			System.out.println("Loaded texture " + name + '!');
		} catch(Exception e) {
			System.err.println("Trouble loading texure!");
			e.printStackTrace();
			textures.get("error");
		}
	}
	
	public Sprite(double x, double y, String name) {
		image = textures.get(name);
		this.x = x;
		this.y = y;
		scaleX = scaleY = 1;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int)x, (int)y, (int)(image.getWidth() * scaleX), (int)(image.getHeight() * scaleY), null);
	}
	
	public void draw(Graphics2D g2d, AffineTransform transform) {
		g2d.drawImage(image, transform, null);
	}

	public void setPosition(double x, double y) {
		this.x = x - image.getWidth() * scaleX / 2;
		this.y = y - image.getHeight() * scaleY / 2;
	}

	public void setScale(double i, double j) {
		scaleX = i;
		scaleY = j;
	}
}
