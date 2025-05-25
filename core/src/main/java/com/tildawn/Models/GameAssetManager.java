package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final Texture logoTexture = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_20Logo.png"));
    private final Texture avatar1Texture = new Texture(Gdx.files.internal("Avatars/avatar1.png"));
    private final Texture avatar2Texture = new Texture(Gdx.files.internal("Avatars/avatar2.png"));
    private final Texture avatar3Texture = new Texture(Gdx.files.internal("Avatars/avatar3.png"));
    private final Texture heroTexture1 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_0 #8325.png"));
    private final Texture heroTexture2 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_0 #8328.png"));
    private final Texture heroTexture3 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_0 #8333.png"));
    private final Texture heroTexture4 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_0 #8327.png"));
    private final Texture heroTexture5 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_0 #8330.png"));
    private final Texture weaponTexture1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/RevolverStill/RevolverStill.png"));
    private final Texture weaponTexture2 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_DualShotgun_Gun.png"));
    private final Texture weaponTexture3 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_DualSMGs_Icon.png"));

    private final Texture dasherIdle1 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_0 #8325.png"));
    private final Texture dasherIdle2 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_1 #8355.png"));
    private final Texture dasherIdle3 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_2 #8814.png"));
    private final Texture dasherIdle4 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_3 #8452.png"));
    private final Texture dasherIdle5 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_4 #8313.png"));
    private final Texture dasherIdle6 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_5 #8302.png"));
    private final Animation<Texture> dasherIdleAnimation = new Animation<>(0.1f, dasherIdle1, dasherIdle2, dasherIdle3, dasherIdle4, dasherIdle5, dasherIdle6);

    private final Texture diamondIdle1 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_0 #8328.png"));
    private final Texture diamondIdle2 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_1 #8358.png"));
    private final Texture diamondIdle3 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_2 #8817.png"));
    private final Texture diamondIdle4 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_3 #8455.png"));
    private final Texture diamondIdle5 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_4 #8316.png"));
    private final Texture diamondIdle6 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_5 #8305.png"));
    private final Animation<Texture> diamondIdleAnimation = new Animation<>(0.1f, diamondIdle1, diamondIdle2, diamondIdle3, diamondIdle4, diamondIdle5, diamondIdle6);


    private final Texture lilithIdle1 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_0 #8333.png"));
    private final Texture lilithIdle2 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_1 #8363.png"));
    private final Texture lilithIdle3 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_2 #8822.png"));
    private final Texture lilithIdle4 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_3 #8460.png"));
    private final Texture lilithIdle5 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_4 #8321.png"));
    private final Texture lilithIdle6 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_5 #8310.png"));
    private final Animation<Texture> lilithIdleAnimation = new Animation<>(0.1f, lilithIdle1, lilithIdle2, lilithIdle3, lilithIdle4, lilithIdle5, lilithIdle6);

    private final Texture scarletIdle1 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_0 #8327.png"));
    private final Texture scarletIdle2 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_1 #8357.png"));
    private final Texture scarletIdle3 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_2 #8816.png"));
    private final Texture scarletIdle4 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_3 #8454.png"));
    private final Texture scarletIdle5 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_4 #8315.png"));
    private final Texture scarletIdle6 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_5 #8304.png"));
    private final Animation<Texture> scarletIdleAnimation = new Animation<>(0.1f, scarletIdle1, scarletIdle2, scarletIdle3, scarletIdle4, scarletIdle5, scarletIdle6);

    private final Texture shanaIdle1 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_0 #8330.png"));
    private final Texture shanaIdle2 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_1 #8360.png"));
    private final Texture shanaIdle3 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_2 #8819.png"));
    private final Texture shanaIdle4 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_3 #8457.png"));
    private final Texture shanaIdle5 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_4 #8318.png"));
    private final Texture shanaIdle6 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_5 #8307.png"));
    private final Animation<Texture> shanaIdleAnimation = new Animation<>(0.1f, shanaIdle1, shanaIdle2, shanaIdle3, shanaIdle4, shanaIdle5, shanaIdle6);



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

    public Texture getHeroTexture1() {
        return heroTexture1;
    }

    public Texture getHeroTexture2() {
        return heroTexture2;
    }

    public Texture getHeroTexture3() {
        return heroTexture3;
    }

    public Texture getHeroTexture4() {
        return heroTexture4;
    }

    public Texture getHeroTexture5() {
        return heroTexture5;
    }

    public Texture getWeaponTexture1() {
        return weaponTexture1;
    }

    public Texture getWeaponTexture2() {
        return weaponTexture2;
    }

    public Texture getWeaponTexture3() {
        return weaponTexture3;
    }

    public Animation<Texture> getDasherIdleAnimation() {
        return dasherIdleAnimation;
    }

    public Animation<Texture> getDiamondIdleAnimation() {
        return diamondIdleAnimation;
    }

    public Animation<Texture> getLilithIdleAnimation() {
        return lilithIdleAnimation;
    }

    public Animation<Texture> getScarletIdleAnimation() {
        return scarletIdleAnimation;
    }

    public Animation<Texture> getShanaIdleAnimation() {
        return shanaIdleAnimation;
    }
}
