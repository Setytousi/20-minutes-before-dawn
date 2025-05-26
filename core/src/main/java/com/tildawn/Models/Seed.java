package com.tildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Seed {
    private Texture texture = GameAssetManager.getGameAssetManager().getSeed();
    private Sprite sprite = new Sprite(texture);
    private float x;
    private float y;
    private boolean isUsed = false;

    public Seed(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setSize(12, 12);
        sprite.setPosition(x, y);
    }


    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return sprite.getBoundingRectangle();
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
