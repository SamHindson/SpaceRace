package co.flamingtrousers.spacerace.universe;

import java.awt.Color;

public class UniverseColors {
	public static final Color TURQUOISE = new Color(0x16a085);
	public static final Color GREEN = new Color(0x27ae60);
	public static final Color BLUE = new Color(0x2980b9);
	public static final Color PURPLE = new Color(0x8e44ad);
	public static final Color ORANGE = new Color(0xf39c12);
	public static final Color DARKORANGE = new Color(0xd35400);
	public static final Color RED = new Color(0xc0392b);
	public static final Color WHITE = new Color(0xbdc3c7);
	public static final Color GREY = new Color(0x7f8c8d);
	
	public static final Color[] colors = new Color[] {
			TURQUOISE, GREEN, BLUE, PURPLE, ORANGE, DARKORANGE, RED, WHITE, GREY
	};
	
	public static final int COUNT = colors.length;
	
	public static Color getRandom() {
		int i = (int)(Math.random() * colors.length);
		System.out.println(i);
		return colors[i];
	}
}
