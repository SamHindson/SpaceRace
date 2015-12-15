package co.flamingtrousers.spacerace.input;

public class Input {
	private static boolean[] keys;
	private static boolean[] buttons;
	
	private static int mouseX, mouseY;
	
	public static void init() {
		keys = new boolean[512];
		buttons = new boolean[32];
	}
	
	public static void setKey(int key, boolean doodoo) {
		keys[key] = doodoo;
	}
	
	public static void setButton(int button, boolean doodoo) {
		buttons[button] = doodoo;
	}
	
	public static void setMousePosition(int x, int y) {
		mouseX = x;
		mouseY = y;
	}
	
	public static int getMouseX() {
		return mouseX;
	}
	
	public static int getMouseY() {
		return mouseY;
	}
	
	public static boolean getKey(int key) {
		return keys[key];
	}
	
	public static boolean getButton(int button) {
		return buttons[button];
	}
}
