package com.tildawn.Models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
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

    public Enemy(Enemies type, float x, float y) {
        this.type = type;
        this.hp = type.getHP();
        this.animation = type.getAnimation();
        this.animationSpawn = type.getSpawnAnimation();
        this.sprite = new Sprite();
        this.sprite.setPosition(x, y);
        this.sprite.setSize(16, 16);
        this.time = 0f;
        this.speed = type.getMoveSpeed();
        this.isSpawning = true;
    }

    public void update(float playerX, float playerY) {
        if (isSpawning) {
            sprite.setRegion(animationSpawn.getKeyFrame(time));
            time += com.badlogic.gdx.Gdx.graphics.getDeltaTime();

            if (animationSpawn.isAnimationFinished(time)) {
                isSpawning = false;
                time = 0f; // reset for regular animation
            }
            return; // don't move yet during spawn animation
        }

        // Move toward player
        Vector2 direction = new Vector2(playerX - sprite.getX(), playerY - sprite.getY()).nor();
        sprite.translate(direction.x * speed, direction.y * speed);

        // Play main animation
        sprite.setRegion(animation.getKeyFrame(time, true));
        time += com.badlogic.gdx.Gdx.graphics.getDeltaTime();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isDead() {
        return hp <= 0;
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
}
