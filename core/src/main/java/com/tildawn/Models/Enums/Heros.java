package com.tildawn.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tildawn.Models.GameAssetManager;

import java.util.ArrayList;

public enum Heros {
    hero1("Dasher", GameAssetManager.getGameAssetManager().getHeroTexture1(), 2, 10,
        GameAssetManager.getGameAssetManager().getDasherIdleAnimation()),
    hero2("Diamond", GameAssetManager.getGameAssetManager().getHeroTexture2(), 2, 10
    , GameAssetManager.getGameAssetManager().getDiamondIdleAnimation()),
    hero3("Lilith", GameAssetManager.getGameAssetManager().getHeroTexture3(), 5, 3
    , GameAssetManager.getGameAssetManager().getLilithIdleAnimation()),
    hero4("Scarlet", GameAssetManager.getGameAssetManager().getHeroTexture4(), 3, 5
    , GameAssetManager.getGameAssetManager().getScarletIdleAnimation()),
    hero5("Shana", GameAssetManager.getGameAssetManager().getHeroTexture5(), 4, 4
    , GameAssetManager.getGameAssetManager().getShanaIdleAnimation()),;

    private final String heroName;
    private final Texture texture;
    private final int HP;
    private final int speed;
    private final Animation<Texture> idleAnimation;


    Heros(String name, Texture texture, int HP, int speed, Animation<Texture> idleAnimation) {
        this.heroName = name;
        this.texture = texture;
        this.HP = HP;
        this.speed = speed;
        this.idleAnimation = idleAnimation;
    }

    public String getHeroName() {
        return heroName;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }

    public Animation<Texture> getIdleAnimation() {
        return idleAnimation;
    }
}
