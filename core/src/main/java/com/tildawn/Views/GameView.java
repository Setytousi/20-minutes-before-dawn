package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.GameController;
import com.tildawn.Main;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private OrthographicCamera camera;

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
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Sprite sprite = controller.getPlayerController().getPlayer().getPlayerSprite();

        float spriteWidth = sprite.getWidth();
        float spriteHeight = sprite.getHeight();

        float playerX = controller.getPlayerController().getPlayer().getPlayerSprite().getX();
        float playerY = controller.getPlayerController().getPlayer().getPlayerSprite().getY();

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

        camera.update();


        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().setProjectionMatrix(camera.combined);
        Main.getBatch().begin();
        controller.updateGame();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
