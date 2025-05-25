package com.tildawn.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.tildawn.Models.GameAssetManager;

public enum Weapons {
    weapon1("revolver", GameAssetManager.getGameAssetManager().getWeaponTexture1(), 20, 1, 1, 6),
    weapon2("shotgun", GameAssetManager.getGameAssetManager().getWeaponTexture2(), 10, 4, 1, 2),
    weapon3("SMGs Dual", GameAssetManager.getGameAssetManager().getWeaponTexture3(), 8, 1, 2, 24);

    private final String name;
    private final Texture texture;
    private final int damage;
    private final int projectile;
    private final int timeReload;
    private final int ammoMax;

    Weapons(String name, Texture texture, int damage, int projectile, int timeReload, int ammoMax) {
        this.name = name;
        this.texture = texture;
        this.damage = damage;
        this.projectile = projectile;
        this.timeReload = timeReload;
        this.ammoMax = ammoMax;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getTimeReload() {
        return timeReload;
    }

    public int getAmmoMax() {
        return ammoMax;
    }
}
