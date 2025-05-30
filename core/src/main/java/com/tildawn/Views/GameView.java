package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.GameController;
import com.tildawn.Controllers.PauseMenuController;
import com.tildawn.Main;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.Player;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private OrthographicCamera camera;
    private Screen nextScreen = null;

    private Stage hudStage;
    private Label timeLabel, ammoLabel, levelLabel, killLabel;
    private ProgressBar xpBar;
    private Table topTable, bottomTable, heartsTable;
    private Image[] hearts;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.3f;
        stage = new Stage(new ScreenViewport());
        hudStage = new Stage(new ScreenViewport());

        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        topTable = new Table();
        topTable.setFillParent(true);
        topTable.top().pad(10);

        bottomTable = new Table();
        bottomTable.setFillParent(true);
        bottomTable.bottom().pad(10);

        heartsTable = new Table();
        int heartCount = controller.getPlayerController().getPlayer().getMaxHp() / 5;
        hearts = new Image[heartCount];

        for (int i = 0; i < heartCount; i++) {
            hearts[i] = new Image(GameAssetManager.getGameAssetManager().getHeartTexture());
            heartsTable.add(hearts[i]).size(32).padRight(2);
        }

        timeLabel = new Label("00:00", skin);
        ammoLabel = new Label("Ammo: 10", skin);
        killLabel = new Label("Kills: 0", skin);

        topTable.add(heartsTable).expandX().left();
        topTable.add(timeLabel).expandX().center();
        topTable.add(ammoLabel).expandX().right();

        levelLabel = new Label("Lvl 1", skin);
        xpBar = new ProgressBar(0, 10, 1, false, skin);
        xpBar.setAnimateDuration(0.2f);
        bottomTable.add(levelLabel).padRight(10);
        bottomTable.add(xpBar).width(300).height(20);
        bottomTable.add(killLabel).padLeft(10);

        hudStage.addActor(topTable);
        hudStage.addActor(bottomTable);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Player player = controller.getPlayerController().getPlayer();
        Sprite sprite = player.getPlayerSprite();

        float spriteWidth = sprite.getWidth();
        float spriteHeight = sprite.getHeight();

        float playerX = sprite.getX();
        float playerY = sprite.getY();

        camera.position.set(playerX + spriteWidth / 2f, playerY + spriteHeight / 2f, 0);

        float camHalfWidth = camera.viewportWidth * camera.zoom / 2f;
        float camHalfHeight = camera.viewportHeight * camera.zoom / 2f;

        float bgX = 0;
        float bgY = 0;
        float bgWidth = controller.getWorldController().getMapWidth();
        float bgHeight = controller.getWorldController().getMapHeight();

        float minX = bgX + camHalfWidth;
        float maxX = bgX + bgWidth - camHalfWidth;

        float minY = bgY + camHalfHeight;
        float maxY = bgY + bgHeight - camHalfHeight;

        camera.position.x = Math.max(minX, Math.min(camera.position.x, maxX));
        camera.position.y = Math.max(minY, Math.min(camera.position.y, maxY));

        camera.update();

        ScreenUtils.clear(0, 0, 0, 1);

        Main.getBatch().setProjectionMatrix(camera.combined);
        Main.getBatch().begin();
        controller.updateGame();
        Main.getBatch().end();

        stage.act(delta);
        stage.draw();

        if (nextScreen != null) {
            dispose();
            Main.getMain().setScreen(nextScreen);
            nextScreen = null;
        }

        updateHud();
        hudStage.act(delta);
        hudStage.draw();
    }

    private void updateHud() {
        Player player = controller.getPlayerController().getPlayer();
        int hp = player.getPlayerHealth();
        int heartsToShow = (hp + 4) / 5;

        for (int i = 0; i < hearts.length; i++) {
            hearts[i].setVisible(i < heartsToShow);
        }

        ammoLabel.setText("Ammo: " + controller.getWeaponController().getWeapon().getAmmo());
        timeLabel.setText(formatTime(controller.getElapsedTime()));
        levelLabel.setText("Lvl " + player.getLevel());
        xpBar.setValue(player.getXp() % 10);
        killLabel.setText("Kills: " + player.getKills());
    }

    private String formatTime(float totalSeconds) {
        int minutes = (int) (totalSeconds / 60);
        int seconds = (int) (totalSeconds % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { if (hudStage != null) hudStage.dispose(); }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.R) {
            controller.getWeaponController().startReload();
            return true;
        }
        if (i == Input.Keys.P) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), controller, GameAssetManager.getGameAssetManager().getSkin()));
        }
        return false;
    }

    @Override public boolean keyUp(int i) { return false; }
    @Override public boolean keyTyped(char c) { return false; }
    @Override public boolean touchDown(int x, int y, int pointer, int button) {
        Vector3 worldCoords = camera.unproject(new Vector3(x, y, 0));
        controller.getWeaponController().fireBullet(worldCoords.x, worldCoords.y);
        return true;
    }
    @Override public boolean touchUp(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchDragged(int i, int i1, int i2) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) {
        Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        controller.getWeaponController().handleWeaponRotation(worldCoords.x, worldCoords.y);
        return false;
    }
    @Override public boolean scrolled(float x, float y) { return false; }

    public GameController getController() { return controller; }
    public void setNextScreen(Screen screen) { this.nextScreen = screen; }
}
