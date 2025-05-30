package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Models.Enums.Enemies;

public class Enemy {
    private Enemies type;;
    private int hp;
    private Animation<Texture> animation;
    private Animation<Texture> animationSpawn;
    private Sprite sprite;
    private float time;
    private float x;
    private float y;
    private float speed;
    private boolean isSpawning;
    private float lastShotTime = 0.0f;
    private boolean isTakingDamage = false;
    private float damageTimer = 0.0f;
    private boolean dead = false;
    private float shootTimer = 0.0f;

    public Enemy(Enemies type, float x, float y) {
        this.type = type;
        this.hp = type.getHP();
        this.animation = type.getAnimation();
        this.animation.setPlayMode(Animation.PlayMode.LOOP);
        this.animationSpawn = type.getSpawnAnimation();
        this.sprite = new Sprite();
        this.sprite.setPosition(x, y);
        if (type.equals(Enemies.tree)){
            this.sprite.setSize(30, 40);
        }
        else if (type.equals(Enemies.elder)){
            this.sprite.setSize(60, 70);
        }
        else this.sprite.setSize(20, 20);
        this.time = 0f;
        this.speed = type.getMoveSpeed();
        this.isSpawning = true;
    }

    public void update(float playerX, float playerY) {
        if (isSpawning) {
            sprite.setRegion(animationSpawn.getKeyFrame(time));
            if (animationSpawn.isAnimationFinished(time)) {
                isSpawning = false;
                time = 0f;
            }
        } else {
            // Move toward player
            Vector2 direction = new Vector2(playerX - sprite.getX(), playerY - sprite.getY()).nor();
            sprite.translate(direction.x * speed, direction.y * speed);

            sprite.setRegion(animation.getKeyFrame(time));
        }
        x = sprite.getX();
        y = sprite.getY();
        time += Gdx.graphics.getDeltaTime();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void takeDamage(float amount) {
        hp -= amount;
    }

    public float getHp() {
        return hp;
    }

    public Enemies getType() {
        return type;
    }

    public boolean isSpawning() {
        return isSpawning;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

    public Rectangle getBounds() {
        return sprite.getBoundingRectangle();
    }

    public float getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(float lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public boolean isTakingDamage() {
        return isTakingDamage;
    }

    public void setTakingDamage(boolean takingDamage) {
        isTakingDamage = takingDamage;
    }

    public float getDamageTimer() {
        return damageTimer;
    }

    public void setDamageTimer(float damageTimer) {
        this.damageTimer = damageTimer;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public float getShootTimer() {
        return shootTimer;
    }

    public void setShootTimer(float shootTimer) {
        this.shootTimer = shootTimer;
        if (shootTimer > 1f){
            shootTimer = 0;
        }
    }
}
