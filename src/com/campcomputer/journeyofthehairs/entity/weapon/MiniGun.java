package com.campcomputer.journeyofthehairs.entity.weapon;

import com.campcomputer.journeyofthehairs.PhysicsEngine;
import com.campcomputer.journeyofthehairs.entity.shot.Bullet;

public class MiniGun extends Weapon {

	public MiniGun(PhysicsEngine engine) {
		super(engine, 150);
	}

	@Override
	protected void addImagesOfEntityToFrames() {

	}

	@Override
	public void shoot() {
		super.shoot();
		PhysicsEngine engine = getEngine();
		Bullet bullet = new Bullet(engine, engine.getPlayer().isFacingLeft());
		engine.addEntity(bullet);

		subtractAmmo();
	}
}
