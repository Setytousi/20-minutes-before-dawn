package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.tildawn.Models.Enums.Heros;

public class Player {
    private Heros heroType;
    private Texture playerTexture;
    private Sprite playerSprite;
    private float posX = 0;
    private float posY = 0;
    private float playerHealth = 100;
    private float time = 0;
    private float speed = 5;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(Heros heroType) {
        this.heroType = heroType;
        this.playerTexture = heroType.getTexture();
        this.playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(18, 20);
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
    }

    public Heros getHeroType() {
        return heroType;
    }

    public void setHeroType(Heros heroType) {
        this.heroType = heroType;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public Rectangle getBounds() {
        return playerSprite.getBoundingRectangle();
    }
}
