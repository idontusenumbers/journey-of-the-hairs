package com.campcomputer.journeyofthehairs.entity.shot;

import com.campcomputer.journeyofthehairs.Images;
import com.campcomputer.journeyofthehairs.PhysicsEngine;
import com.campcomputer.journeyofthehairs.map.MapListener;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Rocket extends Shot {

	private static final int ROCKET_DAMAGE = 50;

	private static final float SPEED_MAGNITUDE = 0.1f;

	List<BufferedImage> rocketLeft;

	List<BufferedImage> rocketRight;

	public Rocket(PhysicsEngine engine, MapListener mapListener) {
		super(engine, mapListener);
	}

	@Override
	public void tick() {
		super.tick();

		float playerX = engine.getPlayer().getX();
		float playerY = engine.getPlayer().getY();

		if (playerX < getX()) {
			frames = rocketLeft;
			setXVelocity(- 1 * SPEED_MAGNITUDE);
		} else {
			frames = rocketRight;
			setXVelocity(SPEED_MAGNITUDE);
		}

		if (playerY < getY()) {
			setYVelocity(- 1 * SPEED_MAGNITUDE);
		} else {
			setYVelocity(SPEED_MAGNITUDE);
		}

		if (engine.isOnTopOfPlayer(this)) {
			engine.getPlayer().setHealth(engine.getPlayer().getHealth() - ROCKET_DAMAGE);
			engine.removeEntity(this);
		}
	}

	@Override
	protected void loadImages() {
		rocketLeft = new ArrayList<BufferedImage>(1);
		rocketRight = new ArrayList<BufferedImage>(1);

		rocketLeft.add(Images.ReadImage("entities/shots/rocket left"));
		rocketRight.add(Images.ReadImage("entities/shots/rocket right"));

		frames = rocketRight;
	}

	public boolean isAffectedByHitDetection() {
		return false;
	}
}