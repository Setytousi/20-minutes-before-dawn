package com.tildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private Texture texture;
    private Sprite sprite;
    private int ammo = 0;
    private int timeReload;

    public Weapon(){
        texture = App.getLoggedInUser().getWeaponType().getTexture();
        sprite = new Sprite(texture);
        ammo = App.getLoggedInUser().getWeaponType().getAmmoMax();
        sprite.setSize(10, 10);
        timeReload = App.getLoggedInUser().getWeaponType().getTimeReload();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getTimeReload() {
        return timeReload;
    }
}
