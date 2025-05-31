package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.MenuControllers.StartMenuController;
import com.tildawn.Main;
import com.tildawn.Models.GameAssetManager;

public class StartMenuView implements Screen {
    private final StartMenuController controller;
    private Stage stage;
    private Table table;
    private final Image logo;
    private final TextButton registerButton;
    private final TextButton loginButton;


    public StartMenuView(StartMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        Texture logoTexture = GameAssetManager.getGameAssetManager().getLogoTexture();
        this.table = new Table();
        this.logo = new Image(logoTexture);
        this.registerButton = new TextButton("Register", skin);
        this.loginButton = new TextButton("Login", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(logo).width(500).height(400);
        table.row().pad(40, 0, 0, 0);
        table.add(registerButton).width(300).height(60);
        table.row().pad(20, 0, 0, 0);
        table.add(loginButton).width(300).height(60);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleStartMenuButtons();
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

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }
}
