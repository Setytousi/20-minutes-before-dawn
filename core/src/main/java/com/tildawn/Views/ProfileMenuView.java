package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tildawn.Controllers.MenuControllers.ProfileMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;

public class ProfileMenuView implements Screen {
    private ProfileMenuController controller;
    private Stage stage;
    private Table table;
    private Label menuLabel;
    private Label errorMessage;
    private Label passwordLabel;
    private Label usernameLabel;
    private Label avatarLabel;
    private TextButton changePasswordButton;
    private TextButton changeUsernameButton;
    private TextButton deleteAccountButton;
    private TextButton backButton;
    private TextField newUsernameTextField;
    private TextField newPasswordTextField;
    private SelectBox<String> avatarSelectBox;
    private Image avatar1;
    private Image avatar2;
    private Image avatar3;
    private Image uploadedAvatarImage;
    private Texture uploadedAvatarTexture;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        this.stage = new Stage();
        this.table = new Table();
        this.menuLabel = new Label("Profile Menu", skin, "title");
        this.errorMessage = new Label("", skin, "subtitle");
        this.passwordLabel = new Label("new password", skin, "subtitle");
        this.usernameLabel = new Label("new username", skin, "subtitle");
        this.avatarLabel = new Label("Choose avatar", skin, "subtitle");
        this.changePasswordButton = new TextButton("Change Password", skin);
        this.changeUsernameButton = new TextButton("Change Username", skin);
        this.newUsernameTextField = new TextField("", skin);
        this.newPasswordTextField = new TextField("", skin);
        this.deleteAccountButton = new TextButton("Delete Account", skin);
        this.backButton = new TextButton("Back", skin);
        this.avatarSelectBox = new SelectBox<>(skin);
        this.avatarSelectBox.setItems("avatar1", "avatar2", "avatar3");
        Texture texture1 = GameAssetManager.getGameAssetManager().getAvatar1Texture();
        Texture texture2 = GameAssetManager.getGameAssetManager().getAvatar2Texture();
        Texture texture3 = GameAssetManager.getGameAssetManager().getAvatar3Texture();
        if (App.getLoggedInUser().getAvatarTexture().equals(texture1)) this.avatarSelectBox.setSelectedIndex(0);
        else if (App.getLoggedInUser().getAvatarTexture().equals(texture2)) this.avatarSelectBox.setSelectedIndex(1);
        else this.avatarSelectBox.setSelectedIndex(2);
        this.avatar1 = new Image(texture1);
        this.avatar2 = new Image(texture2);
        this.avatar3 = new Image(texture3);
    }

    @Override
    public void show() {
        table.setFillParent(true);
        table.center();

        // Title
        table.add(menuLabel).height(30).colspan(2).center();
        table.row().pad(20, 0, 0, 0);

        // Error message
        table.add(errorMessage).colspan(2).center();
        table.row().pad(10, 0, 0, 0);

        // Username
        table.add(usernameLabel).left().padRight(10);
        table.add(newUsernameTextField).width(300).height(60);
        table.add(changeUsernameButton).width(450).height(60).padLeft(10);
        table.row().pad(15, 0, 0, 0);

        // Password
        table.add(passwordLabel).left().padRight(10);
        table.add(newPasswordTextField).width(300).height(60);
        table.add(changePasswordButton).width(450).height(60).padLeft(10);
        table.row().pad(15, 0, 0, 0);

        // Avatar select box
        table.add(avatarLabel).left().padRight(10);
        table.add(avatarSelectBox).width(300);
        // Avatar images (all in one row)
        table.add(avatar1).width(64).height(64).right();
        table.add(avatar2).width(64).height(64);
        table.add(avatar3).width(64).height(64);
        table.row().pad(20, 0, 0, 0);

        // Hint for drag-and-drop
        Label dragHint = new Label("Or drag an image here", avatarLabel.getStyle());
        table.add(dragHint);
        table.row().pad(100, 0, 0, 0);

        // Uploaded avatar if exists
        if (uploadedAvatarImage != null) {
            table.add(uploadedAvatarImage).width(100).height(100).colspan(2).center().padTop(20);
            table.row();
        }

        // Delete account
        table.add(deleteAccountButton).width(400).height(60);
        table.row().pad(30, 0, 0, 0);

        table.add(backButton).width(400).height(60);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void onAvatarFileDropped(String path) {
        Gdx.app.postRunnable(() -> {
            try {
                if (uploadedAvatarTexture != null) uploadedAvatarTexture.dispose();
                uploadedAvatarTexture = new Texture(Gdx.files.absolute(path));
                App.getLoggedInUser().setAvatarTexture(uploadedAvatarTexture);
                App.getLoggedInUser().setAvatarPath(path);

                if (uploadedAvatarImage != null) {
                    uploadedAvatarImage.remove();
                }

                uploadedAvatarImage = new Image(uploadedAvatarTexture);
                uploadedAvatarImage.setSize(64, 64);
                table.add(uploadedAvatarImage).colspan(2).center().padTop(20).width(100).height(100);
                table.row();
            } catch (Exception e) {
                System.out.println("Failed to load image: " + e.getMessage());
            }
        });
    }

    public boolean hasCustomAvatar() {
        return uploadedAvatarTexture != null;
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfileMenuButtons();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setText(errorMessage);
    }

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextField getNewUsernameTextField() {
        return newUsernameTextField;
    }

    public TextField getNewPasswordTextField() {
        return newPasswordTextField;
    }

    public SelectBox<String> getAvatarSelectBox() {
        return avatarSelectBox;
    }
}
