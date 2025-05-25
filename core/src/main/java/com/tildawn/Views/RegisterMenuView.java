package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.MenuControllers.RegisterMenuController;
import com.tildawn.Main;

public class RegisterMenuView implements Screen {
    private final RegisterMenuController controller;
    private Stage stage;
    private Table table;
    private final TextField usernameButton;
    private final TextField passwordButton;
    private final TextField securityQuestionButton;
    private final TextButton loginButton;
    private final TextButton registerButton;
    private final Label menuLabel;
    private final Label securityQuestion;
    private final Label usernameLabel;
    private final Label passwordLabel;
    private final Label successLabel;
    private final Label errorLabel;

    public RegisterMenuView(RegisterMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);


        this.menuLabel = new Label("Register Menu", skin, "title");
        this.usernameButton = new TextField("", skin);
        this.passwordButton = new TextField("", skin);
        passwordButton.setPasswordCharacter('*');
        passwordButton.setPasswordMode(true);
        this.securityQuestion = new Label("What is your favorite animal?", skin, "subtitle");
        this.securityQuestionButton = new TextField("", skin);
        this.registerButton = new TextButton("Register", skin);
        this.loginButton = new TextButton("Login", skin);
        this.usernameLabel = new Label("Username:", skin, "subtitle");
        this.passwordLabel = new Label("Password:", skin, "subtitle");
        this.successLabel = new Label("", skin, "subtitle");
        this.errorLabel = new Label("", skin, "subtitle");

        this.table = new Table();
    }

    public void setErrorLabel(String error) {
        this.errorLabel.setText(error);
    }
    public void setSuccessLabel(String success) {
        this.successLabel.setText(success);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(menuLabel).height(50).colspan(2);
        table.row().pad(30, 0, 0, 0);

        table.add(successLabel).colspan(2).center();
        table.row();
        table.add(errorLabel).colspan(2).center();
        table.row().pad(40, 0, 0, 0);

        table.add(usernameLabel).left().padRight(13);
        table.add(usernameButton).width(300).height(70);
        table.row().pad(10, 0, 0, 0);

        table.add(passwordLabel).left().padRight(13);
        table.add(passwordButton).width(300).height(70);
        table.row().pad(20, 0, 0, 0);

        table.add(securityQuestion).left().padRight(13);
        table.add(securityQuestionButton).width(300).height(70);
        table.row().pad(30, 0, 0, 0);

        table.add(registerButton).width(300).height(60).colspan(2);
        table.row().pad(10, 0, 0, 0);

        table.add(loginButton).width(300).height(60).colspan(2);
        table.row().pad(10, 0, 0, 0);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleRegisterMenuButtons();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public TextField getUsernameButton() {
        return usernameButton;
    }

    public TextField getPasswordButton() {
        return passwordButton;
    }

    public TextField getSecurityQuestionButton() {
        return securityQuestionButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }
}
