package com.campcomputer.journeyofthehairs.entity.shot;

import com.campcomputer.journeyofthehairs.Images;
import com.campcomputer.journeyofthehairs.PhysicsEngine;
import com.campcomputer.journeyofthehairs.entity.Entity;
import com.campcomputer.journeyofthehairs.entity.creatures.Player;
import com.campcomputer.journeyofthehairs.weapon.WeaponShots;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends Shot {
	public List<BufferedImage> bulletLeft;

	public List<BufferedImage> bulletRight;

	private PhysicsEngine engine;

	public Bullet(PhysicsEngine engine, boolean startsLeft) {
		super(engine, startsLeft, WeaponShots.BULLET);
		this.engine = engine;

		frames = startsLeft ? bulletLeft : bulletRight;
	}

	public void tick() {
		if (isFacingLeft()) {
			moveLeft();
		} else {
			moveRight();
		}

		for (Entity entity : engine.getEntities()) {
			Point2D bPosition = new Point2D.Float(getX(), getY());
			Point2D ePosition = new Point2D.Float(entity.getX(), entity.getY());
			if ((bPosition.distance(ePosition) < 1f) && ! (entity instanceof Player) && ! (entity instanceof Bullet)) {
				entity.takeDamageFromPlayer();
				engine.removeEntity(this);
			}
		}
	}

	@Override
	protected void addImagesOfEntityToFrames() {
		bulletLeft = new ArrayList<BufferedImage>();
		bulletRight = new ArrayList<BufferedImage>();
		bulletRight.add(Images.ReadImage("/images/entities/shots/bullet right.png"));
		bulletLeft.add(Images.ReadImage("/images/entities/shots/bullet left.png"));
	}
}
