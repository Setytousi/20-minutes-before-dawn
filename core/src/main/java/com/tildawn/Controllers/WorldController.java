package com.tildawn.Controllers;

import com.badlogic.gdx.graphics.Texture;
import com.tildawn.Main;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private float backgroundX = 0;
    private float backgroundY = 0;
    private int mapWidth;
    private int mapHeight;


    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        mapHeight = backgroundTexture.getHeight();
        mapWidth = backgroundTexture.getWidth();
        this.playerController = playerController;
    }
    public void update() {
        Main.getBatch().draw(backgroundTexture, 0, 0);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
