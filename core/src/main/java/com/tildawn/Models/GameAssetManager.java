package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
    private final Texture bulletTexture = new Texture(Gdx.files.internal("bullet.png"));
    private final Texture seed = new Texture(Gdx.files.internal("Images_grouped_2/seed.png"));


    private final Texture treeMonster1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TreeMonster_0.png"));
    private final Texture treeMonster2 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TreeMonster_1.png"));
    private final Texture treeMonster3 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TreeMonster_2.png"));
    private final Animation<Texture> treeMonsterSpawnAnimation = new Animation<Texture>(0.3f, treeMonster1, treeMonster2, treeMonster3);
    private final Texture treeMonsterSpawn1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TreeMonsterWalking.png"));
    private final Animation<Texture> treeMonsterAnimation = new Animation<>(0.3f, treeMonsterSpawn1);

    private final Texture tentacleMonster1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleSpawn_0.png"));
    private final Texture tentacleMonster2 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleSpawn_1.png"));
    private final Texture tentacleMonster3 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleSpawn_2.png"));
    private final Animation<Texture> tentacleSpawnAnimation = new Animation<>(0.3f, tentacleMonster1, tentacleMonster2, tentacleMonster3);
    private final Texture tentacleMonsterSpawn1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleEnemy_0.png"));
    private final Texture tentacleMonsterSpawn2 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleEnemy_1.png"));
    private final Texture tentacleMonsterSpawn3 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleEnemy_2.png"));
    private final Texture tentacleMonsterSpawn4 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_TentacleEnemy_3.png"));
    private final Animation<Texture> tentacleAnimation = new Animation<>(0.3f, tentacleMonsterSpawn1, tentacleMonsterSpawn2, tentacleMonsterSpawn3, tentacleMonsterSpawn4);


    private final Texture eyeBat1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_EyeBat_EM.png"));
    private final Animation<Texture> eyeBatSpawnAnimation = new Animation<>(0.3f, eyeBat1);
    private final Texture eyeBatSpawn1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_EyeBat_0.png"));
    private final Texture eyeBatSpawn2 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_EyeBat_1.png"));
    private final Texture eyeBatSpawn3 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_EyeBat_2.png"));
    private final Texture eyeBatSpawn4 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/T/T_EyeBat_3.png"));
    private final Animation<Texture> eyeBatAnimation = new Animation<>(0.3f, eyeBatSpawn1, eyeBatSpawn2, eyeBatSpawn3, eyeBatSpawn4);

    private final Animation<Texture> elderBrainSpawnAnimation = new Animation<>(0.3f, new Texture(Gdx.files.internal("Images_grouped_2/Sprite/ElderBrain/ElderBrain_Em.png")));
    private final Animation<Texture> elderBrainAnimation = new Animation<>(0.3f, new Texture(Gdx.files.internal("Images_grouped_2/Sprite/ElderBrain/ElderBrain.png")));


    private final Texture dasherIdle1 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_0 #8325.png"));
    private final Texture dasherIdle2 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_1 #8355.png"));
    private final Texture dasherIdle3 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_2 #8814.png"));
    private final Texture dasherIdle4 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_3 #8452.png"));
    private final Texture dasherIdle5 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_4 #8313.png"));
    private final Texture dasherIdle6 = new Texture(Gdx.files.internal("Heros/Dasher/idle/Idle_5 #8302.png"));
    private final Animation<Texture> dasherIdleAnimation = new Animation<>(0.3f, dasherIdle1, dasherIdle2, dasherIdle3, dasherIdle4, dasherIdle5, dasherIdle6);

    private final Texture diamondIdle1 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_0 #8328.png"));
    private final Texture diamondIdle2 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_1 #8358.png"));
    private final Texture diamondIdle3 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_2 #8817.png"));
    private final Texture diamondIdle4 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_3 #8455.png"));
    private final Texture diamondIdle5 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_4 #8316.png"));
    private final Texture diamondIdle6 = new Texture(Gdx.files.internal("Heros/Diamond/idle/Idle_5 #8305.png"));
    private final Animation<Texture> diamondIdleAnimation = new Animation<>(0.3f, diamondIdle1, diamondIdle2, diamondIdle3, diamondIdle4, diamondIdle5, diamondIdle6);


    private final Texture lilithIdle1 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_0 #8333.png"));
    private final Texture lilithIdle2 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_1 #8363.png"));
    private final Texture lilithIdle3 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_2 #8822.png"));
    private final Texture lilithIdle4 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_3 #8460.png"));
    private final Texture lilithIdle5 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_4 #8321.png"));
    private final Texture lilithIdle6 = new Texture(Gdx.files.internal("Heros/Lilith/idle/Idle_5 #8310.png"));
    private final Animation<Texture> lilithIdleAnimation = new Animation<>(0.3f, lilithIdle1, lilithIdle2, lilithIdle3, lilithIdle4, lilithIdle5, lilithIdle6);

    private final Texture scarletIdle1 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_0 #8327.png"));
    private final Texture scarletIdle2 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_1 #8357.png"));
    private final Texture scarletIdle3 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_2 #8816.png"));
    private final Texture scarletIdle4 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_3 #8454.png"));
    private final Texture scarletIdle5 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_4 #8315.png"));
    private final Texture scarletIdle6 = new Texture(Gdx.files.internal("Heros/Scarlet/idle/Idle_5 #8304.png"));
    private final Animation<Texture> scarletIdleAnimation = new Animation<>(0.3f, scarletIdle1, scarletIdle2, scarletIdle3, scarletIdle4, scarletIdle5, scarletIdle6);

    private final Texture shanaIdle1 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_0 #8330.png"));
    private final Texture shanaIdle2 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_1 #8360.png"));
    private final Texture shanaIdle3 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_2 #8819.png"));
    private final Texture shanaIdle4 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_3 #8457.png"));
    private final Texture shanaIdle5 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_4 #8318.png"));
    private final Texture shanaIdle6 = new Texture(Gdx.files.internal("Heros/Shana/idle/Idle_5 #8307.png"));
    private final Animation<Texture> shanaIdleAnimation = new Animation<>(0.3f, shanaIdle1, shanaIdle2, shanaIdle3, shanaIdle4, shanaIdle5, shanaIdle6);

    private final Texture damage1 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/HeartAnimation/HeartAnimation_0.png"));
    private final Texture damage2 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/HeartAnimation/HeartAnimation_1.png"));
    private final Texture damage3 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/HeartAnimation/HeartAnimation_2.png"));
    private final Texture damage4 = new Texture(Gdx.files.internal("Images_grouped_2/Sprite/HeartAnimation/HeartAnimation_3.png"));
    private final Animation<Texture> damageAnimation = new Animation<>(0.2f, damage1, damage2, damage3, damage4);

    private final Texture enemyDamage1 = new Texture(Gdx.files.internal("Images_grouped_2/Texture2D/ExplosionFX/ExplosionFX_0.png"));
    private final Texture enemyDamage2 = new Texture(Gdx.files.internal("Images_grouped_2/Texture2D/ExplosionFX/ExplosionFX_1.png"));
    private final Texture enemyDamage3 = new Texture(Gdx.files.internal("Images_grouped_2/Texture2D/ExplosionFX/ExplosionFX_2.png"));
    private final Texture enemyDamage4 = new Texture(Gdx.files.internal("Images_grouped_2/Texture2D/ExplosionFX/ExplosionFX_3.png"));
    private final Texture enemyDamage5 = new Texture(Gdx.files.internal("Images_grouped_2/Texture2D/ExplosionFX/ExplosionFX_4.png"));
    private final Texture enemyDamage6 = new Texture(Gdx.files.internal("Images_grouped_2/Texture2D/ExplosionFX/ExplosionFX_5.png"));
    private final Animation<Texture> enemyDamageAnimation = new Animation<>(0.1f, enemyDamage1, enemyDamage2, enemyDamage3, enemyDamage4, enemyDamage5, enemyDamage6);

    private final Texture shieldTexture = new Texture(Gdx.files.internal("shield.png"));

    private Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Pretty Dungeon LOOP.wav"));
    private Music bgMusic1 = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Pretty Dungeon LOOP.wav"));
    private Music bgMusic2 = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/cave themeb4.ogg"));
    private Music UIclick = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/UI Click 36.wav"));
    private Music walkSound = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Footsteps_Casual_Grass_01.wav"));
    private Music enemyDeathSound = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Explosion_Blood_01.wav"));
    private Music playerDamage = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/sfx_sounds_impact1.wav"));
    private Music lowHealthAlarm = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/sfx_lowhealth_alarmloop1.wav"));
    private Music shootingSound = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/single_shot.wav"));
    private Music weaponReload = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Weapon_Shotgun_Reload.wav"));
    private Music nothing = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/1-second-of-silence.mp3"));
    private Music healSound = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/Heal_Short.wav"));
    private Music winSound = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/you-win-sequence-2-183949.mp3"));
    private Music loseSound = Gdx.audio.newMusic(Gdx.files.internal("AudioClip/you-lose-game-sound-230514.mp3"));


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

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public Animation<Texture> getTreeMonsterSpawnAnimation() {
        return treeMonsterSpawnAnimation;
    }

    public Animation<Texture> getTreeMonsterAnimation() {
        return treeMonsterAnimation;
    }

    public Animation<Texture> getTentacleSpawnAnimation() {
        return tentacleSpawnAnimation;
    }

    public Animation<Texture> getEyeBatSpawnAnimation() {
        return eyeBatSpawnAnimation;
    }

    public Animation<Texture> getElderBrainSpawnAnimation() {
        return elderBrainSpawnAnimation;
    }

    public Animation<Texture> getElderBrainAnimation() {
        return elderBrainAnimation;
    }

    public Animation<Texture> getEyeBatAnimation() {
        return eyeBatAnimation;
    }

    public Animation<Texture> getTentacleAnimation() {
        return tentacleAnimation;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(Music backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public Music getUIclick() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return UIclick;
    }

    public Music getBgMusic2() {
        return bgMusic2;
    }

    public Music getBgMusic1() {
        return bgMusic1;
    }

    public Music getWalkSound() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return walkSound;
    }

    public Music getEnemyDeathSound() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return enemyDeathSound;
    }

    public Music getPlayerDamage() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return playerDamage;
    }

    public Music getLowHealthAlarm() {
        return lowHealthAlarm;
    }

    public Music getShootingSound() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return shootingSound;
    }

    public Music getWeaponReload() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return weaponReload;
    }

    public Music getHealSound() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return healSound;
    }

    public Texture getSeed() {
        return seed;
    }

    public TextureRegionDrawable getHeartTexture() {
        return new TextureRegionDrawable(new TextureRegion(new Texture("Images_grouped_2/heart.png")));
    }

    public Music getLoseSound() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return loseSound;
    }

    public Music getWinSound() {
        if (App.getLoggedInUser() != null && !App.getLoggedInUser().isSfxEnabled()){
            return nothing;
        }
        return winSound;
    }

    public Animation<Texture> getDamageAnimation() {
        return damageAnimation;
    }

    public Animation<Texture> getEnemyDamageAnimation() {
        return enemyDamageAnimation;
    }

    public Texture getShieldTexture() {
        return shieldTexture;
    }
}
