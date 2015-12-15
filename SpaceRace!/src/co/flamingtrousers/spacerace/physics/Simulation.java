package co.flamingtrousers.spacerace.physics;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Simulation {
	ArrayList<Rekt> rekts;

	public Simulation() {
		System.out.println("Physics Sim Initialized!");
		rekts = new ArrayList<>();
	}

	public void add(Rekt what) {
		rekts.add(what);
	}

	public void update(double dt) {
		for (int t = 0; t < rekts.size() - 1; t++) {
			for (int r = t + 1; r < rekts.size(); r++) {
				doRektVsRekt(rekts.get(t), rekts.get(r));
			}
		}

		for (Rekt rekt : rekts) {
			rekt.update(dt);
		}
	}

	private void doRektVsRekt(Rekt rekt1, Rekt rekt2) {
		Vec2[] axes = new Vec2[8];

		axes[0] = new Vec2(rekt1.getVert(0).x - rekt1.getVert(1).x, rekt1.getVert(0).y - rekt1.getVert(1).y);
		axes[1] = new Vec2(rekt1.getVert(1).x - rekt1.getVert(2).x, rekt1.getVert(1).y - rekt1.getVert(2).y);
		axes[2] = new Vec2(rekt2.getVert(0).x - rekt2.getVert(1).x, rekt2.getVert(0).y - rekt2.getVert(1).y);
		axes[3] = new Vec2(rekt2.getVert(1).x - rekt2.getVert(2).x, rekt2.getVert(1).y - rekt2.getVert(2).y);
		
		for (int i = 0; i < 4; i++) {
			double[] one = new double[4];
			double[] two = new double[4];

			for (int j = 0; j < 4; j++) {
				one[j] = Vec2.project(rekt1.getVert(j), axes[i]);
				two[j] = Vec2.project(rekt2.getVert(j), axes[i]);
			}
			
			double minB = min(two);
			double minA = min(one);
			double maxB = max(two);
			double maxA = max(one);
			
			boolean overlap = minB <= maxA && maxB >= minA;
		}
	}
	
	private double min(double[] vals) {
		double min = Double.MAX_VALUE;
		
		for(double mine : vals) {
			if(mine < min)
				min = mine;
		}
		
		return min;
	}
	
	private double max(double[] vals) {
		double min = -Double.MAX_VALUE;
		
		for(double mine : vals) {
			if(mine > min)
				min = mine;
		}
		
		return min;
	}

	public void render(Graphics2D g2d) {
		for (Rekt rekt : rekts) {
			rekt.draw(g2d);
		}
	}
}
