package com.tildawn.Controllers;

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

    public EnemyController(int totalTime, int mapWidth, int mapHeight) {
        this.totalTime = totalTime * 60;
        spawnInitialTrees(10, mapWidth, mapHeight);
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
                for (int i = 0; i < (4 * elapsedTime - totalTime + 30) / 30; i++){
                    spawnEyebat(mapWidth, mapHeight);
                }
                elderTime = 0;
            }
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
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

            Sprite s = enemy.getSprite();
            s.draw(Main.getBatch());

            // Check collisions with bullets
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if (bullet.getBounds().overlaps(enemy.getBounds()) && !enemy.isSpawning()) {
                    enemy.takeDamage(App.getLoggedInUser().getWeaponType().getDamage());
                    bulletIterator.remove();
                    break;
                }
            }
            Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
            while (enemyBulletIterator.hasNext()) {
                Bullet b = enemyBulletIterator.next();
                Sprite sprite = b.getSprite();
                sprite.translate(b.getDirection().x * 0.1f, b.getDirection().y * 0.1f);
                sprite.draw(Main.getBatch());

                // Remove if off-screen
                if (sprite.getX() < -50 || sprite.getX() > mapWidth + 50 ||
                    sprite.getY() < -50 || sprite.getY() > mapHeight + 50) {
                    enemyBulletIterator.remove();
                }
            }


            // Remove dead enemies
            if (enemy.isDead()) {
                iterator.remove();
                GameAssetManager.getGameAssetManager().getEnemyDeathSound().play();
                Seed seed = new Seed(enemy.getX(), enemy.getY());
                seeds.add(seed);
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
