package com.tildawn.Controllers;

import com.tildawn.Models.App;
import com.tildawn.Models.Player;
import com.tildawn.Models.Weapon;
import com.tildawn.Views.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private int mapWidth;
    private int mapHeight;

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(new Player(App.getLoggedInUser().getHeroType()));
        worldController = new WorldController(playerController);
        mapWidth = worldController.getMapWidth();
        mapHeight = worldController.getMapHeight();
        weaponController = new WeaponController(new Weapon());
    }

    public void updateGame() {
        if (view != null) {
            worldController.update();
            playerController.update(mapWidth, mapHeight);
            weaponController.update(playerController.getPlayer().getPosX(), playerController.getPlayer().getPosY());
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
