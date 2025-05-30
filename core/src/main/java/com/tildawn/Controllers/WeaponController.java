package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.tildawn.Main;
import com.tildawn.Models.*;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Models.Enums.Enemies;


import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private boolean canShoot = true;
    private float timeReload;
    private float reloadEndTime = 0;
    private boolean isReloading = false;
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private GameController gameController;

    public WeaponController(Weapon weapon, GameController gameController) {
        this.gameController = gameController;
        this.weapon = weapon;
        this.timeReload = weapon.getTimeReload();
    }

    public void update(float playerX, float playerY) {
        Sprite weaponSprite = weapon.getSprite();

        float offsetX = 16f;
        float offsetY = 5f;

        weaponSprite.setPosition(playerX + offsetX, playerY + offsetY);
        weapon.getSprite().draw(Main.getBatch());
        weapon.getSprite().setOriginCenter();
        if (isReloading && TimeUtils.nanoTime() > reloadEndTime) {
            canShoot = true;
            isReloading = false;
        }
        updateBullets();
    }

    public void handleWeaponRotation(float mouseWorldX, float mouseWorldY) {
        Sprite weaponSprite = weapon.getSprite();

        // Center of the weapon sprite in world coordinates
        float weaponCenterX = weaponSprite.getX() + weaponSprite.getWidth() / 2f;
        float weaponCenterY = weaponSprite.getY() + weaponSprite.getHeight() / 2f;

        // Direction vector from weapon to mouse
        Vector2 direction = new Vector2(mouseWorldX - weaponCenterX, mouseWorldY - weaponCenterY);

        // Calculate angle in degrees and apply rotation
        float angleDegrees = direction.angleDeg();
        weaponSprite.setRotation(angleDegrees);
    }


    public void updateBullets() {
        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            Sprite bulletSprite = b.getSprite();
            Vector2 direction = b.getDirection();

            // Move the bullet
            bulletSprite.translate(direction.x * 3, direction.y * 3);

            // Draw the bullet
            bulletSprite.draw(Main.getBatch());

            // Optional: remove bullet if it goes off-screen
            if (bulletSprite.getX() < -300 || bulletSprite.getX() > Gdx.graphics.getWidth() + 300 ||
                bulletSprite.getY() < -300 || bulletSprite.getY() > Gdx.graphics.getHeight() + 300) {
                iterator.remove();
            }
        }
    }

    public void fireBullet(float mouseWorldX, float mouseWorldY) {
        if (!canShoot) return;
        Sprite weaponSprite = weapon.getSprite();

        if (weapon.getAmmo() <= 0) {
            if (App.getLoggedInUser().isAutoReload()) {
                startReload();
            }
            return;
        }
        weapon.setAmmo(weapon.getAmmo() - 1);

        GameAssetManager.getGameAssetManager().getShootingSound().play();
        // Weapon center
        float weaponCenterX = weaponSprite.getX() + weaponSprite.getWidth() / 2f;
        float weaponCenterY = weaponSprite.getY() + weaponSprite.getHeight() / 2f;

        // Base direction
        Vector2 baseDirection = new Vector2(mouseWorldX - weaponCenterX, mouseWorldY - weaponCenterY).nor();

        int projectileCount = App.getLoggedInUser().getWeaponType().getProjectile() + gameController.getPlayerController().getPlayer().getProjectileAdded();
        float spreadAngle = 1f;

        for (int i = 0; i < projectileCount; i++) {
            float angleOffset = (i - projectileCount / 2f) * spreadAngle;
            Vector2 spreadDir = baseDirection.cpy().rotateDeg(angleOffset).nor();

            Bullet bullet = new Bullet(weaponCenterX, weaponCenterY, spreadDir);
            bullets.add(bullet);
        }
    }


    public void startReload() {
        GameAssetManager.getGameAssetManager().getWeaponReload().play();
        isReloading = true;
        canShoot = false;
        reloadEndTime = TimeUtils.nanoTime() + (long) (timeReload * 1_000_000_000L);
        weapon.setAmmo(App.getLoggedInUser().getWeaponType().getAmmoMax() + gameController.getPlayerController().getPlayer().getAmmoMaxAdded());
    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void autoAim(float playerX, float playerY) {
        ArrayList<Enemy> enemies = gameController.getEnemyController().getEnemies();
        if (enemies.isEmpty()) return;

        Enemy closestEnemy = null;
        float closestDistance = Float.MAX_VALUE;

        for (Enemy enemy : enemies) {
            if (enemy.isDead() || enemy.isSpawning() || enemy.getType().equals(Enemies.tree)) continue;

            float enemyX = enemy.getX() + enemy.getSprite().getWidth() / 2f;
            float enemyY = enemy.getY() + enemy.getSprite().getHeight() / 2f;
            float distance = new Vector2(playerX, playerY).dst(enemyX, enemyY);

            if (distance < closestDistance) {
                closestDistance = distance;
                closestEnemy = enemy;
            }
        }

        if (closestEnemy != null) {
            float weaponCenterX = playerX;
            float weaponCenterY = playerY;

            float enemyX = closestEnemy.getX() + closestEnemy.getSprite().getWidth() / 2f;
            float enemyY = closestEnemy.getY() + closestEnemy.getSprite().getHeight() / 2f;
            Vector2 direction = new Vector2(enemyX - weaponCenterX, enemyY - weaponCenterY).nor();

            fireBullet(enemyX, enemyY);
        }
    }
}
