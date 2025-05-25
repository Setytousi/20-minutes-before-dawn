package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.MenuControllers.LoginMenuController;
import com.tildawn.Main;

public class LoginMenuView implements Screen {
    private final LoginMenuController controller;
    private Stage stage;
    private Table table;
    private final TextField usernameButton;
    private final TextField passwordButton;
    private final TextField usernameForgetButton;
    private final TextField passwordForgetButton;
    private final TextField securityQuestionForgetButton;

    private final TextButton loginButton;
    private final TextButton exitMenuButton;
    private final TextButton changePasswordButton;
    private final TextButton guestButton;

    private final Label forgetPasswordLabel;
    private final Label menuLabel;
    private final Label securityQuestion;
    private final Label usernameLabel;
    private final Label passwordLabel;
    private final Label successLabel;
    private final Label errorLabel;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        this.menuLabel = new Label("Login Menu", skin, "title");
        this.usernameButton = new TextField("", skin);
        this.passwordButton = new TextField("", skin);
        this.usernameForgetButton = new TextField("", skin);
        this.passwordForgetButton = new TextField("", skin);
        this.securityQuestionForgetButton = new TextField("", skin);
        passwordButton.setPasswordCharacter('*');
        passwordButton.setPasswordMode(true);

        this.forgetPasswordLabel = new Label("Forget Password", skin, "subtitle");

        this.usernameLabel = new Label("Username:", skin, "subtitle");
        this.passwordLabel = new Label("Password:", skin, "subtitle");
        this.successLabel = new Label("", skin, "subtitle");
        this.errorLabel = new Label("", skin, "subtitle");
        this.securityQuestion = new Label("What is your favorite animal?", skin, "subtitle");

        this.exitMenuButton = new TextButton("Exit menu", skin);
        this.guestButton = new TextButton("Guest", skin);
        this.loginButton = new TextButton("Login", skin);
        this.changePasswordButton = new TextButton("Change Password", skin);

        this.table = new Table();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        // Title
        table.add(menuLabel).height(20).colspan(2).center();
        table.row().pad(5, 0, 0, 0);

        // Success & Error Messages
        table.add(successLabel).colspan(2).center();
        table.row();
        table.add(errorLabel).colspan(2).center();
        table.row().pad(5, 0, 0, 0);

        // --- Login Section ---
        table.add(usernameLabel).left().padRight(5);
        table.add(usernameButton).width(300).height(60);
        table.row().pad(4, 0, 0, 0); // tighter spacing

        table.add(passwordLabel).left().padRight(5);
        table.add(passwordButton).width(300).height(60);
        table.row().pad(4, 0, 0, 0); // tighter spacing

        // Login and Guest Buttons — side by side
        table.add(loginButton).width(300).height(50).padLeft(300);
        table.row().pad(0, 0, 0, 0);
        table.add(guestButton).width(300).height(50).center().padLeft(300);
        table.row().pad(30, 0, 0, 0);

        // --- Forget Password Section ---
        table.add(forgetPasswordLabel).colspan(2).center();
        table.row().pad(10, 0, 0, 0);

        table.add(new Label("Username:", usernameLabel.getStyle())).left().padRight(13);
        table.add(usernameForgetButton).width(300).height(60);
        table.row().pad(8, 0, 0, 0);

        table.add(securityQuestion).left().padRight(13);
        table.add(securityQuestionForgetButton).width(300).height(60);
        table.row().pad(8, 0, 0, 0);

        table.add(new Label("New Password:", passwordLabel.getStyle())).left().padRight(13);
        table.add(passwordForgetButton).width(300).height(60);
        table.row().pad(10, 0, 0, 0);

        // Change Password Button
        table.add(changePasswordButton).width(450).height(50).colspan(2);
        table.row().pad(15, 0, 0, 0);

        // Exit Button
        table.add(exitMenuButton).width(300).height(50).colspan(2);
        table.row();

        // Add table to stage
        stage.addActor(table);
    }

    public void setErrorLabel(String error) {
        this.errorLabel.setText(error);
    }
    public void setSuccessLabel(String success) {
        this.successLabel.setText(success);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleLoginMenuButtons();
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

    public TextField getUsernameButton() {
        return usernameButton;
    }

    public TextField getPasswordButton() {
        return passwordButton;
    }

    public TextField getUsernameForgetButton() {
        return usernameForgetButton;
    }

    public TextField getPasswordForgetButton() {
        return passwordForgetButton;
    }

    public TextField getSecurityQuestionForgetButton() {
        return securityQuestionForgetButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getExitMenuButton() {
        return exitMenuButton;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getGuestButton() {
        return guestButton;
    }
}
