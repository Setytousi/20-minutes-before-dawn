package com.tildawn.Controllers;

import com.tildawn.Models.Enemy;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Main;
import com.tildawn.Models.Enums.Enemies;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyController {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float spawnTimer = 0;
    private float spawnInterval = 5;
    private float totalTime = 0;
    private float elapsedTime = 0;

    public EnemyController(int totalTime){
        this.totalTime = totalTime * 60;
    }

    public void update(float delta, float playerX, float playerY, int mapWidth, int mapHeight) {
        spawnTimer += delta;

        elapsedTime += delta;


        if (spawnTimer >= spawnInterval) {
            spawnEnemy(mapWidth, mapHeight);
            spawnTimer = 0;
        }

        // Update and draw enemies
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(playerX, playerY);
            Sprite s = enemy.getSprite();
            s.draw(Main.getBatch());

            if (enemy.isDead()) {
                iterator.remove();
                // TODO: Drop item
            }
        }
    }

    private void spawnEnemy(int mapWidth, int mapHeight) {
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

        Enemies randomType = MathUtils.randomBoolean(0.8f)
            ? Enemies.tentacle
            : Enemies.eyebat; // pick based on time later

        enemies.add(new Enemy(randomType, x, y));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
