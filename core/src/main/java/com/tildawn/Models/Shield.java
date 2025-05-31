package com.tildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shield {
    private float x, y, width, height;
    private boolean active = true;
    private float shrinkSpeed = 20f;
    private Texture shieldTexture;


    public Shield(float mapWidth, float mapHeight) {
        this.x = 0;
        this.y = 0;
        this.width = mapWidth;
        this.height = mapHeight;
        shieldTexture = GameAssetManager.getGameAssetManager().getShieldTexture();
    }

    public void update(float delta) {
        if (!active) return;
        width -= shrinkSpeed * delta;
        height -= shrinkSpeed * delta;
        if (width <= 0 || height <= 0) {
            active = false;
        }
    }

    public boolean collidesWith(float playerX, float playerY, float playerWidth, float playerHeight) {
        if (!active) return false;
        return playerX < x + width && playerX + playerWidth > x &&
            playerY < y + height && playerY + playerHeight > y;
    }

    public boolean isActive() {
        return active;
    }

    public void destroy() {
        active = false;
    }

    public void draw(SpriteBatch batch, Texture texture) {
        if (active) {
            batch.draw(texture, x, y, width, height);
        }
    }

    public Texture getShieldTexture() {
        return shieldTexture;
    }

    public float getShrinkSpeed() {
        return shrinkSpeed;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}

