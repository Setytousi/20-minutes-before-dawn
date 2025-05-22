package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tildawn.Controllers.MainMenuController;
import com.tildawn.Controllers.StartMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;

public class MainMenuView implements Screen {
    private final MainMenuController controller;

    private Stage stage;
    private Table table;
    private final Label menuLabel;
    private final Image avatar;
    private final Label username;
    private final Label score;
    private final TextButton newGame;
    private final TextButton resumeGame;
    private final TextButton options;
    private final TextButton profile;
    private final TextButton scoreBoard;
    private final TextButton talentMenu;
    private final TextButton logout;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.table = new Table();
        this.menuLabel = new Label("Main Menu", skin, "title");
        this.avatar = new Image(App.getLoggedInUser().getAvatarTexture());
        this.username = new Label(App.getLoggedInUser().getUsername(), skin, "subtitle");
        this.score = new Label("Score: " + App.getLoggedInUser().getScore(), skin, "subtitle");
        this.newGame = new TextButton("New Game", skin);
        this.resumeGame = new TextButton("Resume Game", skin);
        this.options = new TextButton("Options", skin);
        this.profile = new TextButton("Profile", skin);
        this.logout = new TextButton("Logout", skin);
        this.scoreBoard = new TextButton("ScoreBoard", skin);
        this.talentMenu = new TextButton("Talent Menu", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        table.setFillParent(true);
        table.center();

        // Title
        table.add(menuLabel).height(30).colspan(3).center(); // colspan 3 for avatar + username + score
        table.row().pad(50, 0, 0, 0);

        // Avatar | Username | Score (all in one row)
        table.add(avatar).width(80).height(80).padRight(20);
        table.add(username).left().padRight(20);
        table.add(score).left();
        table.row().pad(30, 0, 0, 0); // space before buttons

        // Buttons (each takes full row)
        table.add(newGame).width(300).height(55).colspan(3);
        table.row().pad(10, 0, 0, 0);

        table.add(resumeGame).width(300).height(55).colspan(3);
        table.row().pad(10, 0, 0, 0);

        table.add(options).width(300).height(55).colspan(3);
        table.row().pad(10, 0, 0, 0);

        table.add(profile).width(300).height(55).colspan(3);
        table.row().pad(10, 0, 0, 0);

        table.add(scoreBoard).width(300).height(55).colspan(3);
        table.row().pad(10, 0, 0, 0);

        table.add(talentMenu).width(300).height(55).colspan(3);
        table.row().pad(10, 0, 0, 0);

        table.add(logout).width(300).height(55).colspan(3);

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
        controller.handleMainMenuButtons();
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

    public TextButton getNewGame() {
        return newGame;
    }

    public TextButton getResumeGame() {
        return resumeGame;
    }

    public TextButton getOptions() {
        return options;
    }

    public TextButton getProfile() {
        return profile;
    }

    public TextButton getLogout() {
        return logout;
    }

    public TextButton getScoreBoard() {
        return scoreBoard;
    }

    public TextButton getTalentMenu() {
        return talentMenu;
    }
}
