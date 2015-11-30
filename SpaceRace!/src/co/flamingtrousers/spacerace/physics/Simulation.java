package co.flamingtrousers.spacerace.physics;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Simulation {
	ArrayList<AABB> abs;

	public Simulation() {
		System.out.println("Physics Sim Initialized!");
		abs = new ArrayList<>();
	}

	public void add(AABB what) {
		abs.add(what);
	}

	public void update(double dt) {
		for (AABB aabb : abs) {
			aabb.update(dt);

			for (AABB aabb2 : abs) {
				if (!aabb.equals(aabb2)) {
					
				}
			}
		}
	}

	private void handleCollision(AABB ab1, AABB ab2, Manifold m) {
		Vec2 normal = new Vec2(1, 0);
		// System.out.println("Normal: " + normal);

		double restitution = Math.min(ab1.getRestitution(), ab2.getRestitution());
		// System.out.println("Resitiution: " + restitution);

		Vec2 relativeVelocity = ab2.getVelocity().sub(ab1.getVelocity());
		// System.out.println("Relative Vel: " + relativeVelocity);

		double velocityAlongNormal = relativeVelocity.dot(normal);
		// System.out.println("Vel along normal: " + velocityAlongNormal);

		if (velocityAlongNormal > 0) {
			return;
		}

		double impulseScalar = -(1 + restitution) * velocityAlongNormal;
		impulseScalar /= 1 / ab1.getMass() + 1 / ab2.getMass();
		// System.out.println("Impulse Scalar: " + impulseScalar);

		Vec2 impulse = normal.mul(impulseScalar);
		// System.out.println("The Impulse: " + impulse);

		ab1.editVelocity(impulse.mul(-1 / ab1.getMass()));
		ab2.editVelocity(impulse.mul(1 / ab2.getMass()));
		// System.out.println();
		// ab2.setVelocity(Vec2.ZERO);
	}

	public void render(Graphics2D g2d) {
		for (AABB aabb : abs) {
			aabb.draw(g2d);
		}
	}
}
