package co.flamingtrousers.spacerace;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import co.flamingtrousers.spacerace.graphics.Sprite;
import co.flamingtrousers.spacerace.input.Input;
import co.flamingtrousers.spacerace.input.InputManager;
import co.flamingtrousers.spacerace.screens.ScreenManager;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 729303269231262510L;
	
	public static final int width = 250;
	public static final int height = width / 4 * 3;
	public static final int scale = 3;
	
	public static double elapsedTime;
	public static double dt;
	public static int fps;
	private static int lastTime = -1;
	
	public static final boolean DEBUG = true;

	public static final double GRAV = 0.1;
	
	private Thread graphicsThread;
	private JFrame frame;
	
	private InputManager inputManager;
	
	private boolean running = false;
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);		
		
		ScreenManager.init();
		
		frame = new JFrame();
		frame.add(this);                                       
		frame.setResizable(false);                             
		frame.setTitle("SpaceRace!");                          
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.pack();                                          
		frame.setLocationRelativeTo(null);                     
		frame.setVisible(true);
		
		requestFocusInWindow();
		requestFocus();
		
		inputManager = new InputManager();
		addMouseListener(inputManager);
		addMouseMotionListener(inputManager);
		addKeyListener(inputManager);
		
		start();
	}
	
	public synchronized void start() {
		running = true;
		graphicsThread = new Thread(this, "SpaceRace! Graphics");
		graphicsThread.start();
	}
	
	public synchronized void stop() {
		try {
			graphicsThread.join();
		} catch (InterruptedException e) {
			System.err.println("Oh hell no! There's no stopping this train!!");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long then, now;
		
		then = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			dt = (now - then) / 1000000000.0;
			
			update(dt);
			render();
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			then = now;
		}
	}

	private void update(double d) {
		elapsedTime += dt;
		
		ScreenManager.update(d);
	}

	private void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		
		if(bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g2d = (Graphics2D)bufferStrategy.getDrawGraphics();
		ScreenManager.render(g2d);
		g2d.dispose();
		
		bufferStrategy.show();
	}
	
	public static int getGameWidth() {
		return width;
	}
	
	public static int getGameHeight() {
		return height;
	}
	
	public static int getScreenWidth() {
		return width * scale;
	}
	
	public static int getScreenHeight() {
		return height * scale;
	}

	public static void main(String[] args) {
		Game game = new Game();
	}

}
