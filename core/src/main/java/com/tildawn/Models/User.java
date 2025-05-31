package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.tildawn.Models.Enums.Heros;
import com.tildawn.Models.Enums.Weapons;

import java.util.Random;

public class User {
    private String username;
    private String password;
    private String securityQuestionAnswer;
    private int score;
    private Texture avatarTexture;
    private Game savedGame;
    private int left;
    private int right;
    private int up;
    private int down;
    private float soundVolume;
    private int music;
    private boolean sfxEnabled;
    private boolean autoReload;
    private boolean blackAndWhite;
    private int maxAliveTime;
    private int killNumber;
    private String avatarPath;
    private Heros heroType;
    private Weapons weaponType;
    private int gameTime;

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        if (avatarPath != null) {
            avatarTexture = new Texture(Gdx.files.internal(avatarPath));
        }
    }

    public User(String username, String password, String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.score = 0;
        this.heroType = Heros.hero1;
        {
            Random rand = new Random();
            int randomNum = rand.nextInt(3);
            if (randomNum == 0) {
                setAvatarPath("Avatars/avatar1.png");
            } else if (randomNum == 1) {
                setAvatarPath("Avatars/avatar2.png");
            } else {
                setAvatarPath("Avatars/avatar2.png");
            }
        }
        this.savedGame = null;
        this.left = Input.Keys.A;
        this.right = Input.Keys.D;
        this.up = Input.Keys.W;
        this.down = Input.Keys.S;
        this.soundVolume = 0.5f;
        this.music = 0;
        this.sfxEnabled = true;
        this.autoReload = false;
        this.blackAndWhite = false;
        this.weaponType = Weapons.weapon1;
        this.gameTime = 2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public int getScore() {
        return score;
    }

    public Texture getAvatarTexture() {
        return avatarTexture;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Game getSavedGame() {
        return savedGame;
    }

    public void setSavedGame(Game savedGame) {
        this.savedGame = savedGame;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public void setSfxEnabled(boolean sfxEnabled) {
        this.sfxEnabled = sfxEnabled;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public void setAvatarTexture(Texture avatarTexture) {
        this.avatarTexture = avatarTexture;
    }

    public int getMaxAliveTime() {
        return maxAliveTime;
    }

    public int getKillNumber() {
        return killNumber;
    }

    public void setMaxAliveTime(int maxAliveTime) {
        this.maxAliveTime = maxAliveTime;
    }

    public void setKillNumber(int killNumber) {
        this.killNumber = killNumber;
    }

    public Heros getHeroType() {
        return heroType;
    }

    public Weapons getWeaponType() {
        return weaponType;
    }

    public void setHeroType(Heros heroType) {
        this.heroType = heroType;
    }

    public void setWeaponType(Weapons weaponType) {
        this.weaponType = weaponType;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

}
