package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tildawn.Controllers.MenuControllers.OptionMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;

public class OptionMenuView implements Screen {
    private OptionMenuController controller;

    private Stage stage;
    private Table table;
    private final Label menuLabel;
    private Slider volumeSlider;
    private Label volumeLabel;
    private SelectBox<String> musicSelectBox;
    private SelectBox<String> sfxSelectBox;
    private SelectBox<String> controlsSelectBox;
    private SelectBox<String> autoReloadSelectBox;
    private SelectBox<String> blackAndWhiteSelectBox;
    private TextButton backButton;

    public OptionMenuView(OptionMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        this.stage = new Stage();
        this.table = new Table();
        this.menuLabel = new Label("Options Menu", skin, "title");
        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin); // min=0, max=1, step=0.01
        this.volumeSlider.setValue(App.getLoggedInUser().getSoundVolume());
        this.volumeLabel = new Label("Volume", skin, "subtitle");
        this.musicSelectBox = new SelectBox<>(skin);
        musicSelectBox.setItems("Music 1", "Music 2");
        musicSelectBox.setSelectedIndex(App.getLoggedInUser().getMusic());
        this.sfxSelectBox = new SelectBox<>(skin);
        sfxSelectBox.setItems("On", "Off");
        sfxSelectBox.setSelectedIndex(App.getLoggedInUser().isSfxEnabled() ? 0 : 1);
        this.controlsSelectBox = new SelectBox<>(skin);
        controlsSelectBox.setItems("WASD", "Arrow Keys");
        controlsSelectBox.setSelectedIndex(App.getLoggedInUser().getLeft() == Input.Keys.A ? 0 : 1);
        this.autoReloadSelectBox = new SelectBox<>(skin);
        autoReloadSelectBox.setItems("On", "Off");
        autoReloadSelectBox.setSelectedIndex(App.getLoggedInUser().isAutoReload() ? 0 : 1);
        this.blackAndWhiteSelectBox = new SelectBox<>(skin);
        blackAndWhiteSelectBox.setItems("On", "Off");
        blackAndWhiteSelectBox.setSelectedIndex(App.getLoggedInUser().isBlackAndWhite() ? 0 : 1);
        this.backButton = new TextButton("Back", skin);
    }

    @Override
    public void show() {
        table.setFillParent(true);
        table.center();

        // Title
        table.add(menuLabel).height(30).colspan(2).center();
        table.row().pad(50, 0, 0, 0);

        // Volume
        table.add(volumeLabel).left().padRight(10);
        table.add(volumeSlider).width(300);
        table.row().pad(15, 0, 0, 0);

        // Music SelectBox
        table.add(new Label("Music:", volumeLabel.getStyle())).left().padRight(10);
        table.add(musicSelectBox).width(300);
        table.row().pad(15, 0, 0, 0);

        // SFX SelectBox
        table.add(new Label("SFX:", volumeLabel.getStyle())).left().padRight(10);
        table.add(sfxSelectBox).width(300);
        table.row().pad(15, 0, 0, 0);

        // Controls SelectBox
        table.add(new Label("Controls:", volumeLabel.getStyle())).left().padRight(10);
        table.add(controlsSelectBox).width(300);
        table.row().pad(15, 0, 0, 0);

        // Auto Reload SelectBox
        table.add(new Label("Auto Reload:", volumeLabel.getStyle())).left().padRight(10);
        table.add(autoReloadSelectBox).width(300);
        table.row().pad(15, 0, 0, 0);

        // Black & White Mode SelectBox
        table.add(new Label("Black & White:", volumeLabel.getStyle())).left().padRight(10);
        table.add(blackAndWhiteSelectBox).width(300);
        table.row().pad(50, 0, 0, 0);

        table.add(backButton).width(300).height(60).padLeft(200);
        table.row().pad(15, 0, 0, 0);

        stage.addActor(table);
        com.badlogic.gdx.Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleOptionMenuButtons();
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

    public Slider getVolumeSlider() {
        return volumeSlider;
    }


    public SelectBox<String> getMusicSelectBox() {
        return musicSelectBox;
    }

    public SelectBox<String> getSfxSelectBox() {
        return sfxSelectBox;
    }

    public SelectBox<String> getControlsSelectBox() {
        return controlsSelectBox;
    }

    public SelectBox<String> getAutoReloadSelectBox() {
        return autoReloadSelectBox;
    }

    public SelectBox<String> getBlackAndWhiteSelectBox() {
        return blackAndWhiteSelectBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
