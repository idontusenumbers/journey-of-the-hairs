package com.campcomputer.journeyofthehairs.item;

import com.campcomputer.journeyofthehairs.entity.Entity;
import com.campcomputer.journeyofthehairs.GameEngine;
import com.campcomputer.journeyofthehairs.Images;

public class GrenadeGun extends Item {
    private int Damage = 1;
    //  How much damage the gun does.
    private int FireRate = 1;
    // How many bullets fired per second or something.
    private int BulletNumber = 1;
    // Number of bullets fired.
    private float BulletSpeed = 1;
    // Speed of bullets.

    protected void loadImages() {
        frames.add(Images.ReadImage("images/grenadeLauncher.png"));
    }

    @Override
    public void attack(Entity entity) {
    }

    public GrenadeGun(GameEngine engine) {
        super(engine);
    }
}