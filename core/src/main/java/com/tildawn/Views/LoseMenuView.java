package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.GameController;
import com.tildawn.Controllers.MenuControllers.MainMenuController;
import com.tildawn.Database;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;

import javax.xml.crypto.Data;

public class LoseMenuView implements Screen {
    private Stage stage;
    private Table table;
    private Label youLoseLabel;
    private Label usernameLabel;
    private Label timeLabel;
    private Label numberOfKillsLabel;
    private Label scoreLabel;
    private TextButton backButton;

    public LoseMenuView(GameController gameController, Skin skin) {
        GameAssetManager.getGameAssetManager().getLoseSound().play();
        table = new Table();
        youLoseLabel = new Label("YOU ARE DEAD", skin, "title");
        usernameLabel = new Label(App.getLoggedInUser().getUsername() , skin, "subtitle");
        timeLabel = new Label("you survived for " + formatTime(gameController.getElapsedTime()), skin, "subtitle");
        numberOfKillsLabel = new Label(gameController.getPlayerController().getPlayer().getKills() + " kills", skin, "subtitle");
        int score = (gameController.getPlayerController().getPlayer().getKills() * ((int) gameController.getElapsedTime() / 60));
        scoreLabel = new Label("score : " + score, skin, "subtitle");
        Database.updateUserScore(App.getLoggedInUser().getUsername(), App.getLoggedInUser().getScore() + score);
        App.getLoggedInUser().setScore(App.getLoggedInUser().getScore() + score);
        Database.updateUserKills(App.getLoggedInUser().getUsername(), Math.max(App.getLoggedInUser().getKillNumber(), gameController.getPlayerController().getPlayer().getKills()));
        App.getLoggedInUser().setKillNumber(Math.max(App.getLoggedInUser().getKillNumber(), gameController.getPlayerController().getPlayer().getKills()));
        Database.updateUserMaxAliveTime(App.getLoggedInUser().getUsername(), Math.max(App.getLoggedInUser().getMaxAliveTime(), gameController.getElapsedTime() / 60));
        App.getLoggedInUser().setMaxAliveTime(Math.max(App.getLoggedInUser().getMaxAliveTime(), gameController.getElapsedTime() / 60));
        backButton = new TextButton("back", skin);
    }

    private String formatTime(float totalSeconds) {
        int minutes = (int) (totalSeconds / 60);
        int seconds = (int) (totalSeconds % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(youLoseLabel);
        table.row().pad(20, 0, 0, 0);
        table.add(usernameLabel);
        table.row().pad(10, 0, 0, 0);
        table.add(timeLabel);
        table.row().pad(10, 0, 0, 0);
        table.add(numberOfKillsLabel);
        table.row().pad(10, 0, 0, 0);
        table.add(scoreLabel);
        table.row().pad(30, 0, 0, 0);

        table.add(backButton);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if (backButton.isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
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
}
