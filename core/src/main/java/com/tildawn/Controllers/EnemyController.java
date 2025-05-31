package com.tildawn.Controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Models.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Main;
import com.tildawn.Models.Enums.Enemies;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.abs;

public class EnemyController {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> enemyBullets = new ArrayList<>();
    private ArrayList<Seed> seeds = new ArrayList<>();
    private float totalTime = 0;
    private float elapsedTime = 0;
    private float eps = 0.1f;
    private float tentacleTime = 0;
    private float elderTime = 0;
    private GameController gameController;
    private Animation<Texture> damageAnimation;
    boolean elderSpawned = false;

    public EnemyController(int totalTime, int mapWidth, int mapHeight, GameController gameController) {
        this.totalTime = totalTime * 60;
        this.gameController = gameController;
        spawnInitialTrees(10, mapWidth, mapHeight);
        damageAnimation = GameAssetManager.getGameAssetManager().getEnemyDamageAnimation();
    }

    public void update(float delta, float playerX, float playerY, int mapWidth, int mapHeight, ArrayList<Bullet> bullets) {
        elapsedTime += delta;
        tentacleTime += delta;
        elderTime += delta;
        if (tentacleTime >= 3){
            for (int i = 0; i < (elapsedTime + 29f) / 30f; i++){
                spawnTentacle(mapWidth, mapHeight);
            }
            tentacleTime = 0;
        }
        if (elapsedTime * 4 > totalTime){
            if (elderTime >= 10){
                for (int i = 0; i < (2 * elapsedTime - totalTime + 30) / 30; i++){
                    spawnEyebat(mapWidth, mapHeight);
                }
                elderTime = 0;
            }
        }
        if (elapsedTime * 2 > totalTime && !elderSpawned){
            spawnElder(mapWidth, mapHeight);
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.getShootTimer() > 0f) enemy.setShootTimer(enemy.getShootTimer() + delta);
            enemy.update(playerX, playerY);
            if (enemy.getType() == Enemies.eyebat && !enemy.isSpawning()) {
                if (elapsedTime - enemy.getLastShotTime() >= 5f) {
                    float ex = enemy.getX() + enemy.getSprite().getWidth() / 2f;
                    float ey = enemy.getY() + enemy.getSprite().getHeight() / 2f;
                    Vector2 dir = new Vector2(playerX - ex, playerY - ey).nor();
                    Bullet bullet = new Bullet(ex, ey, dir);
                    enemyBullets.add(bullet);
                    enemy.setLastShotTime(elapsedTime);
                }
            }
            if (enemy.getType() == Enemies.elder && !enemy.isSpawning()) {
                if (elapsedTime - enemy.getLastShotTime() >= 5f) {
                    float ex = enemy.getX() + enemy.getSprite().getWidth() / 2f;
                    float ey = enemy.getY() + enemy.getSprite().getHeight() / 2f;
                    Vector2 dir = new Vector2(playerX - ex, playerY - ey).nor();
                    Bullet bullet = new Bullet(ex, ey, dir);
                    enemyBullets.add(bullet);
                    enemy.setLastShotTime(elapsedTime);
                }
            }
            if (enemy.isTakingDamage()){
                Texture frame = damageAnimation.getKeyFrame(enemy.getDamageTimer());
                Main.getBatch().draw(frame, enemy.getX(), enemy.getY(), enemy.getSprite().getWidth(), enemy.getSprite().getHeight());
                enemy.setDamageTimer(enemy.getDamageTimer() + delta);

                if (enemy.getDamageTimer() >= 0.6f){
                    enemy.setDead(true);
                    gameController.getPlayerController().getPlayer().setKills(gameController.getPlayerController().getPlayer().getKills() + 1);
                    GameAssetManager.getGameAssetManager().getEnemyDeathSound().play();
                    Seed seed = new Seed(enemy.getX(), enemy.getY());
                    seeds.add(seed);
                    iterator.remove();
                }
            }
            else {
                Sprite s = enemy.getSprite();
                s.draw(Main.getBatch());

                // Check collisions with bullets
                Iterator<Bullet> bulletIterator = bullets.iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if (bullet.getBounds().overlaps(enemy.getBounds()) && !enemy.isSpawning()) {
                        enemy.takeDamage(App.getLoggedInUser().getWeaponType().getDamage());
                        if (gameController.getPlayerController().isDamager()) {
                            enemy.takeDamage(App.getLoggedInUser().getWeaponType().getDamage() / 4f);
                        }
                        if (enemy.getHp() <= 0f) {
                            GameAssetManager.getGameAssetManager().getShootingSound().play();

                            enemy.setTakingDamage(true);
                            enemy.setDamageTimer(0f);
                        }
                        bulletIterator.remove();
                        break;
                    }
                }
            }
            Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
            while (enemyBulletIterator.hasNext()) {
                Bullet b = enemyBulletIterator.next();
                Sprite sprite = b.getSprite();
                sprite.translate(b.getDirection().x * 0.1f, b.getDirection().y * 0.1f);
                sprite.draw(Main.getBatch());

                if (sprite.getX() < -50 || sprite.getX() > mapWidth + 50 ||
                    sprite.getY() < -50 || sprite.getY() > mapHeight + 50) {
                    enemyBulletIterator.remove();
                }
            }
        }

        Iterator<Seed> seedIterator = seeds.iterator();
        while (seedIterator.hasNext()) {
            Seed seed = seedIterator.next();
            if (seed.isUsed()){
                seedIterator.remove();
            }
            else{
                Sprite s = seed.getSprite();
                s.draw(Main.getBatch());
            }
        }
    }

    private void spawnEyebat(int mapWidth, int mapHeight) {
        float x, y;

        int side = MathUtils.random(3); // 0=left, 1=top, 2=right, 3=bottom

        switch (side) {
            case 0: // left
                x = 0;
                y = MathUtils.random(0, mapHeight);
                break;
            case 1: // top
                x = MathUtils.random(0, mapWidth);
                y = mapHeight;
                break;
            case 2: // right
                x = mapWidth;
                y = MathUtils.random(0, mapHeight);
                break;
            case 3: // bottom
                x = MathUtils.random(0, mapWidth);
                y = 0;
                break;
            default:
                x = y = 0;
        }
        boolean check = false;
        for (Enemy enemy : enemies) {
            if (abs(enemy.getX() - x) + abs(enemy.getY() - y) <= eps) {
                check = true;
            }
        }
        if (!check) {
            enemies.add(new Enemy(Enemies.eyebat, x, y));
        }
    }

    private void spawnTentacle(int mapWidth, int mapHeight) {
        float x, y;

        int side = MathUtils.random(3); // 0=left, 1=top, 2=right, 3=bottom

        switch (side) {
            case 0: // left
                x = 0;
                y = MathUtils.random(0, mapHeight);
                break;
            case 1: // top
                x = MathUtils.random(0, mapWidth);
                y = mapHeight;
                break;
            case 2: // right
                x = mapWidth;
                y = MathUtils.random(0, mapHeight);
                break;
            case 3: // bottom
                x = MathUtils.random(0, mapWidth);
                y = 0;
                break;
            default:
                x = y = 0;
        }
        boolean check = false;
        for (Enemy enemy : enemies) {
            if (abs(enemy.getX() - x) + abs(enemy.getY() - y) <= eps) {
                check = true;
            }
        }
        if (!check) {
            enemies.add(new Enemy(Enemies.tentacle, x, y));
        }
    }
    private void spawnElder(int mapWidth, int mapHeight) {
        float x, y;

        int side = MathUtils.random(3); // 0=left, 1=top, 2=right, 3=bottom

        switch (side) {
            case 0: // left
                x = 0;
                y = MathUtils.random(0, mapHeight);
                break;
            case 1: // top
                x = MathUtils.random(0, mapWidth);
                y = mapHeight;
                break;
            case 2: // right
                x = mapWidth;
                y = MathUtils.random(0, mapHeight);
                break;
            case 3: // bottom
                x = MathUtils.random(0, mapWidth);
                y = 0;
                break;
            default:
                x = y = 0;
        }
        boolean check = false;
        for (Enemy enemy : enemies) {
            if (abs(enemy.getX() - x) + abs(enemy.getY() - y) <= eps) {
                check = true;
            }
        }
        if (!check){
            elderSpawned = true;
            enemies.add(new Enemy(Enemies.elder, x, y));
        }
    }

    private void spawnInitialTrees(int count, int mapWidth, int mapHeight) {
        for (int i = 0; i < count; i++) {
            float x = MathUtils.random(0, mapWidth - 16);
            float y = MathUtils.random(0, mapHeight - 16);

            boolean tooClose = false;
            for (Enemy e : enemies) {
                if (Math.abs(e.getX() - x) + Math.abs(e.getY() - y) < 50) {
                    tooClose = true;
                    break;
                }
            }

            if (!tooClose) {
                enemies.add(new Enemy(Enemies.tree, x, y));
            }
        }
    }


    public ArrayList<Seed> getSeeds() {
        return seeds;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }
}
