package com.tildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class User {
    private String username;
    private String password;
    private String securityQuestionAnswer;
    private int score;
    private Texture avatarTexture;
    private Game savedGame;

    public User(String username, String password, String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.score = 0;
        {
            Random rand = new Random();
            int randomNum = rand.nextInt(3);
            if (randomNum == 0) {
                avatarTexture = GameAssetManager.getGameAssetManager().getAvatar1Texture();
            } else if (randomNum == 1) {
                avatarTexture = GameAssetManager.getGameAssetManager().getAvatar2Texture();
            } else {
                avatarTexture = GameAssetManager.getGameAssetManager().getAvatar3Texture();
            }
        }
        this.savedGame = null;
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
}
