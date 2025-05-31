package com.tildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;


import javax.swing.*;

public class Bullet {
    private Texture texture = GameAssetManager.getGameAssetManager().getBulletTexture();
    private Sprite sprite = new Sprite(texture);
    private float x;
    private float y;
    private Vector2 direction;

    public Bullet(float x, float y, Vector2 direction) {
        this.sprite = new Sprite(GameAssetManager.getGameAssetManager().getBulletTexture());
        this.sprite.setPosition(x, y);
        this.x = x;
        this.y = y;
        sprite.setSize(20, 15);
        this.direction = direction;
    }


    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public Rectangle getBounds() {
        return sprite.getBoundingRectangle();
    }
}
