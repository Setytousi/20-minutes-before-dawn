package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tildawn.Controllers.MenuControllers.StartMenuController;
import com.tildawn.Main;
import com.tildawn.Models.*;
import com.tildawn.Views.ChooseAbilityView;
import com.tildawn.Views.GameView;
import com.tildawn.Views.StartMenuView;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerController {
    private Player player;
    private Float speedyTimer = 0f;
    private Float damagerTimer = 0f;
    private boolean speedy = false;
    private boolean damager = false;
    private Float hpTimer = 0f;
    private float damageCooldownTimer = 0f;
    private boolean isTakingDamage = false;
    private float damageAnimTime = 0f;


    public PlayerController(Player player){
        this.player = player;
    }


    public void update(int mapWidth, int mapHeight, EnemyController enemyController, GameView gameView, float deltaTime) {
        damageCooldownTimer += deltaTime;
        if (speedy) {
            speedyTimer += deltaTime;
            if (speedyTimer > 10f) {
                speedy = false;
                speedyTimer = 0f;
            }
        }
        if (damager) {
            damagerTimer += deltaTime;
            if (damagerTimer > 10f) {
                damager = false;
                damagerTimer = 0f;
            }
        }
        hpTimer += deltaTime;
        if (hpTimer > 30f) {
            player.setPlayerHealth(player.getPlayerHealth() + 1);
            hpTimer = 0f;
        }

        player.getPlayerSprite().draw(Main.getBatch());

        if (isTakingDamage) {
            Animation<Texture> anim = GameAssetManager.getGameAssetManager().getDamageAnimation();
            Texture frame = anim.getKeyFrame(damageAnimTime);

            float scale = 0.5f;
            float originalWidth = player.getPlayerSprite().getWidth();
            float originalHeight = player.getPlayerSprite().getHeight();

            float newWidth = originalWidth * scale;
            float newHeight = originalHeight * scale;

            float drawX = player.getPlayerSprite().getX() + 12 + (originalWidth - newWidth) / 2 + damageAnimTime;
            float drawY = player.getPlayerSprite().getY() + 12 + (originalHeight - newHeight) / 2 + damageAnimTime;

            Main.getBatch().draw(frame, drawX, drawY, newWidth, newHeight);

            damageAnimTime += deltaTime;

            if (anim.isAnimationFinished(damageAnimTime)) {
                isTakingDamage = false;
                damageAnimTime = 0f;
            }
        }


        if (player.isPlayerIdle()){
            idleAnimation();
        }

        ArrayList<Bullet> enemyBullets = enemyController.getEnemyBullets();
        ArrayList<Enemy> enemies = enemyController.getEnemies();

        Iterator<Bullet> bulletIterator = enemyBullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (bullet.getBounds().overlaps(player.getPlayerSprite().getBoundingRectangle())) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);

                isTakingDamage = true;
                damageAnimTime = 0f;

                GameAssetManager.getGameAssetManager().getPlayerDamage().play();
                if (player.getPlayerHealth() <= 10) {
                    GameAssetManager.getGameAssetManager().getLowHealthAlarm().play();
                }
                bulletIterator.remove();
            }
        }

        for (Enemy enemy : enemies) {
            if (!enemy.isSpawning() &&
                enemy.getSprite().getBoundingRectangle().overlaps(player.getPlayerSprite().getBoundingRectangle())) {

                if (damageCooldownTimer >= 1.5f) {
                    player.setPlayerHealth(player.getPlayerHealth() - 1);
                    GameAssetManager.getGameAssetManager().getPlayerDamage().play();

                    isTakingDamage = true;
                    damageAnimTime = 0f;

                    if (player.getPlayerHealth() <= 10) {
                        GameAssetManager.getGameAssetManager().getLowHealthAlarm().play();
                    }

                    damageCooldownTimer = 0f;
                }

                break;
            }
        }
        Shield elderShield = gameView.getController().getEnemyController().getElderShield();
        if (elderShield != null && elderShield.isActive()) {
            elderShield.update(deltaTime);
            elderShield.draw(Main.getBatch(), elderShield.getShieldTexture());

            Player player = gameView.getController().getPlayerController().getPlayer();
            if (elderShield.collidesWith(player.getPosX(), player.getPosY(), player.getPlayerSprite().getWidth(), player.getPlayerSprite().getHeight())) {
                if (damageCooldownTimer >= 1.5f) {
                    player.setPlayerHealth(player.getPlayerHealth() - 1);
                    isTakingDamage = true;
                    damageAnimTime = 0f;
                    GameAssetManager.getGameAssetManager().getPlayerDamage().play();
                    if (player.getPlayerHealth() <= 10) {
                        GameAssetManager.getGameAssetManager().getLowHealthAlarm().play();
                    }
                    damageCooldownTimer = 0f;
                }
            }
        }


        ArrayList<Seed> seeds = enemyController.getSeeds();
        Iterator<Seed> seedIterator = seeds.iterator();
        while (seedIterator.hasNext()) {
            Seed seed = seedIterator.next();
            if (seed.getBounds().overlaps(player.getPlayerSprite().getBoundingRectangle())) {
                int currentLevel = player.getLevel();
                player.setXp(player.getXp() + 1);
                GameAssetManager.getGameAssetManager().getHealSound().play();
                seed.setUsed(true);
                if (player.getLevel() > currentLevel) {
                    if (player.getLevel() > currentLevel) {
                        gameView.setNextScreen(new ChooseAbilityView(GameAssetManager.getGameAssetManager().getSkin(), gameView));
                    }
                }
            }
        }


        handlePlayerInput(mapWidth, mapHeight);
    }

    public void handlePlayerInput(int mapWidth, int mapHeight) {
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getDown())){
            player.setPosY(player.getPosY() - player.getSpeed() * (speedy ? 2 : 1));
            GameAssetManager.getGameAssetManager().getWalkSound().play();
        }
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getLeft())){
            player.setPosX(player.getPosX() - player.getSpeed() * (speedy ? 2 : 1));
            player.getPlayerSprite().flip(true, false);
            GameAssetManager.getGameAssetManager().getWalkSound().play();
        }
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getUp())){
            player.setPosY(player.getPosY() + player.getSpeed() * (speedy ? 2 : 1));
            GameAssetManager.getGameAssetManager().getWalkSound().play();
        }
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getRight())){
            player.setPosX(player.getPosX() + player.getSpeed() * (speedy ? 2 : 1));
            GameAssetManager.getGameAssetManager().getWalkSound().play();
        }

        float bgX = 0;
        float bgY = 0;
        float bgWidth = mapWidth;
        float bgHeight = mapHeight;

        float spriteWidth = player.getPlayerSprite().getWidth();
        float spriteHeight = player.getPlayerSprite().getHeight();

        float minX = bgX;
        float maxX = bgX + bgWidth - spriteWidth;

        float minY = bgY;
        float maxY = bgY + bgHeight - spriteHeight;

        if (player.getPosX() < minX) player.setPosX(minX);
        if (player.getPosX() > maxX) player.setPosX(maxX);
        if (player.getPosY() < minY) player.setPosY(minY);
        if (player.getPosY() > maxY) player.setPosY(maxY);

        player.getPlayerSprite().setPosition(player.getPosX(), player.getPosY());
    }

    public void idleAnimation(){
        Animation<Texture> animation = player.getHeroType().getIdleAnimation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public Float getSpeedyTimer() {
        return speedyTimer;
    }

    public Float getDamagerTimer() {
        return damagerTimer;
    }

    public boolean isSpeedy() {
        return speedy;
    }

    public boolean isDamager() {
        return damager;
    }

    public void setSpeedy(boolean speedy) {
        this.speedy = speedy;
    }

    public void setDamager(boolean damager) {
        this.damager = damager;
    }
}
