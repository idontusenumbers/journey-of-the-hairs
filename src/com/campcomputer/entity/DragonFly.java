package com.campcomputer.entity;

import com.campcomputer.Entity;
import com.campcomputer.GameEngine;
import com.campcomputer.Images;

public class DragonFly extends Entity {
    private int flyingEnergy;
    private int breathFireDamage = 33;
    private int eatingDamage = 10;

    public DragonFly(GameEngine engine) {
        super(engine);
        setAffectedByGravity(false);
        setHealth(5);
    }

    @Override
    public void tick() {
        super.tick();

        if (engine.isPlayerAbove(this) == 0) {
            moveUp();
        } else if (engine.isPlayerAbove(this) == 1) {
            moveDown();
        } else if (engine.isPlayerAbove(this) == 2) {
            moveUp();
        }
        if (engine.isPlayerToLeft(this) == 0) {
            moveLeft();
        } else if (engine.isPlayerToLeft(this) == 1) {
            moveRight();
        }
    }

    @Override
    protected void loadImages() {
        frames.add(Images.ReadImage("images/dragonfly.png"));
    }

    @Override
    public void attack(Entity entity) {
        if (engine.isPlayerClose(this) && entity.getHealth() <= 10)
            eats(entity);
        else if (engine.isPlayerClose(this))
            breathfire(entity);
    }

    /**
     * Attempts to eat the entity. Since the DragonFlies are small, it doesn't do a lot of damage to a player, but the
     * dragonfly gains health from eating.
     *
     * @param entity the thing to eat
     */
    public void eats(Entity entity) {
        // make the enemy lose health
        entity.setHealth(entity.getHealth() - this.eatingDamage);

        if (entity.getHealth() <= 0) {
            // makes the dragonfly gain health
            setHealth(getHealth() + eatingDamage * 1 / 2);
        }
    }


    /**
     * Breathe fire onto the given entity, usually a player. Instantly does a bunch of damage to it.
     *
     * @param entity the thing to breathe fire on.
     */
    public void breathfire(Entity entity) {
        entity.setHealth(entity.getHealth() - this.breathFireDamage);
    }

    public void chasePlayer1() {
        if (flyingEnergy > 0) {
            // fly towards the player,
            // solid object in the way
        }
        if (flyingEnergy < 0) {
            //walk towards the player,
            // can also jump.
        }
    }


    public int getBreathFireDamage() {
        return breathFireDamage;
    }

    public void setBreathFireDamage(int breathFireDamage) {
        this.breathFireDamage = breathFireDamage;
    }

    public int getEatingDamage() {
        return eatingDamage;
    }

    public void setEatingDamage(int eatingDamage) {
        this.eatingDamage = eatingDamage;
    }

    public int getFlyingEnergy() {
        return flyingEnergy;
    }

    public void setFlyingEnergy(int flyingEnergy) {
        this.flyingEnergy = flyingEnergy;
    }

    public boolean isAffectedByHitDetection() {
        return false;
    }
}