package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.GameController;
import com.tildawn.Controllers.PauseMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;

public class PauseMenuView implements Screen {
    private PauseMenuController controller;
    private Stage stage;
    private Table table;
    private TextButton resumeButton;
    private Label abilites;
    private GameController gameController;
    private Label ability1;
    private Label ability2;
    private Label ability3;
    private TextButton giveUpButton;
    private SelectBox<String> blackAndWhiteSelectBox;
    private TextButton saveAndExitButton;
    private Label cheat1;
    private Label cheat2;
    private Label cheat3;
    private Label cheat4;
    private Label cheat5;
    private Label cheatCode;

    public PauseMenuView(PauseMenuController controller, GameController gameController, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.gameController = gameController;
        table = new Table();
        resumeButton = new TextButton("Resume", skin);
        abilites = new Label("ABILITIES", skin, "subtitle");
        ability1 = new Label("", skin);
        if (gameController.getPlayerController().getPlayer().getMaxHp() > 90){
            ability1 = new Label("VITALITY", skin, "subtitle");
        }
        ability2 = new Label("", skin, "subtitle");
        if (gameController.getPlayerController().getPlayer().getProjectileAdded() > 0){
            ability2 = new Label("PROCREASE", skin, "subtitle");
        }
        ability3 = new Label("", skin, "subtitle");
        if (gameController.getPlayerController().getPlayer().getAmmoMaxAdded() > 0){
            ability3 = new Label("AMOCREASE", skin, "subtitle");
        }
        giveUpButton = new TextButton("Give Up", skin);
        this.blackAndWhiteSelectBox = new SelectBox<>(skin);
        blackAndWhiteSelectBox.setItems("B & W", "COLOR");
        blackAndWhiteSelectBox.setSelectedIndex(App.getLoggedInUser().isBlackAndWhite() ? 0 : 1);
        saveAndExitButton = new TextButton("Save and Exit", skin);
        cheatCode = new Label("Cheat Codes:", skin, "subtitle");
        cheat1 = new Label("T : adds 1 minute to elapsed time", skin);
        cheat2 = new Label("L : increases player level", skin);
        cheat3 = new Label("H : adds health if health = 0", skin);
        cheat4 = new Label("F : gives you 1000 ammo", skin);
        cheat5 = new Label("B : boss fight", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(abilites);
        table.row().pad(10, 0, 0, 0);
        table.add(ability1);
        table.add(ability2);
        table.add(ability3);
        table.row().pad(20, 0, 0, 0);

        table.add(cheatCode);
        table.row().pad(5, 0, 0, 0);
        table.add(cheat1);
        table.row();
        table.add(cheat2);
        table.row();
        table.add(cheat3);
        table.row();
        table.add(cheat4);
        table.row();
        table.add(cheat5);
        table.row().pad(5, 0, 0, 0);

        table.add(blackAndWhiteSelectBox);
        table.row().pad(50, 0, 0, 0);
        table.add(resumeButton);
        table.row().pad(10, 0, 0, 0);
        table.add(saveAndExitButton);
        table.row().pad(10, 0, 0, 0);
        table.add(giveUpButton);
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
        controller.handlePauseMenuButtons();
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

    public TextButton getResumeButton() {
        return resumeButton;
    }

    public TextButton getGiveUpButton() {
        return giveUpButton;
    }

    public SelectBox<String> getBlackAndWhiteSelectBox() {
        return blackAndWhiteSelectBox;
    }

    public TextButton getSaveAndExitButton() {
        return saveAndExitButton;
    }

    public GameController getGameController() {
        return gameController;
    }
}
