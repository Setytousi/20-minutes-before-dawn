package com.tildawn.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tildawn.Models.GameAssetManager;

public enum Enemies {

    tree("tree", 100000000, 0f, GameAssetManager.getGameAssetManager().getTreeMonsterSpawnAnimation(), GameAssetManager.getGameAssetManager().getTreeMonsterAnimation()),
    tentacle("tentacle", 10, 0.1f, GameAssetManager.getGameAssetManager().getTentacleSpawnAnimation(), GameAssetManager.getGameAssetManager().getTentacleAnimation()),
    eyebat("eyebat", 16, 0.2f, GameAssetManager.getGameAssetManager().getEyeBatSpawnAnimation(), GameAssetManager.getGameAssetManager().getEyeBatAnimation()),
    elder("elder", 400, 0.5f, GameAssetManager.getGameAssetManager().getElderBrainSpawnAnimation(), GameAssetManager.getGameAssetManager().getElderBrainAnimation()),
    ;



    private String name;
    private int HP;
    private float moveSpeed;
    private Animation<Texture> spawnAnimation;
    private Animation<Texture> animation;

    Enemies(String name, int HP, float moveSpeed, Animation<Texture> spawnAnimation, Animation<Texture> animation) {
        this.name = name;
        this.HP = HP;
        this.moveSpeed = moveSpeed;
        this.spawnAnimation = spawnAnimation;
        this.animation = animation;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public Animation<Texture> getSpawnAnimation() {
        return spawnAnimation;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }
}
