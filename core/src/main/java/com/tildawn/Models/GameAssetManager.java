package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final Texture logoTexture = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_20Logo.png"));
    private final Texture avatar1Texture = new Texture(Gdx.files.internal("Avatars/avatar1.png"));
    private final Texture avatar2Texture = new Texture(Gdx.files.internal("Avatars/avatar2.png"));
    private final Texture avatar3Texture = new Texture(Gdx.files.internal("Avatars/avatar3.png"));

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getLogoTexture() {
        return logoTexture;
    }

    public Texture getAvatar1Texture() {
        return avatar1Texture;
    }
    public Texture getAvatar2Texture() {
        return avatar2Texture;
    }
    public Texture getAvatar3Texture() {
        return avatar3Texture;
    }

}
