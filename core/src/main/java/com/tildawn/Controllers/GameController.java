package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.tildawn.Models.App;
import com.tildawn.Models.Player;
import com.tildawn.Models.Weapon;
import com.tildawn.Views.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemyController enemyController;
    private int mapWidth;
    private int mapHeight;
    private float elapsedTime;

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(new Player(App.getLoggedInUser().getHeroType()));
        worldController = new WorldController(playerController);
        mapWidth = worldController.getMapWidth();
        mapHeight = worldController.getMapHeight();
        enemyController = new EnemyController(App.getLoggedInUser().getGameTime(), mapWidth, mapHeight, this);
        weaponController = new WeaponController(new Weapon(), this);
    }

    public void updateGame() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (view != null) {
            worldController.update();
            playerController.update(mapWidth, mapHeight, enemyController, view, Gdx.graphics.getDeltaTime());
            weaponController.update(playerController.getPlayer().getPosX(), playerController.getPlayer().getPosY());
            enemyController.update(Gdx.graphics.getDeltaTime(), playerController.getPlayer().getPosX(), playerController.getPlayer().getPosY(), mapWidth, mapHeight, weaponController.getBullets());
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

    public GameView getView() {
        return view;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }
}
