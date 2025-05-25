package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.MenuControllers.ScoreBoardMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.User;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardMenuView implements Screen {
    private ScoreBoardMenuController controller;
    private Stage stage;
    private Table table;
    private Label menuLabel;
    private TextButton backButton;
    private SelectBox<String> sortDropdown;
    private Skin skin;
    private ArrayList<User> users;

    public ScoreBoardMenuView(ScoreBoardMenuController controller, Skin skin) {
        this.controller = controller;
        this.controller.setView(this);
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.sortDropdown = new SelectBox<>(skin);
        this.sortDropdown.setItems("sort by username", "sort by score", "sort by kills", "sort by max alive time");
        this.sortDropdown.setSelectedIndex(0);
        this.table = new Table();
        this.menuLabel = new Label("ScoreBoard", skin, "title");
        this.backButton = new TextButton("Back", skin);
        this.controller.setView(this);
        this.users = new ArrayList<>();
        for (User user : App.getUsers()) {
            this.users.add(user);
        }
        users.sort(Comparator.comparing(User::getUsername));
    }

    private void sortUsersByUsername() {
        users.sort(Comparator.comparing(User::getUsername));
    }
    private void sortUsersByScore() {
        users.sort(Comparator.comparing(User::getScore));
    }
    private void sortUsersByKills() {
        users.sort(Comparator.comparing(User::getKillNumber));
    }
    private void sortUsersByMaxAliveTime() {
        users.sort(Comparator.comparing(User::getMaxAliveTime));
    }




    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        buildTable();

        sortDropdown.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                switch (sortDropdown.getSelectedIndex()) {
                    case 0: sortUsersByUsername(); break;
                    case 1: sortUsersByScore(); break;
                    case 2: sortUsersByKills(); break;
                    case 3: sortUsersByMaxAliveTime(); break;
                }
                refreshUserRows();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleBackButton();
            }
        });


        stage.addActor(table);
    }

    private void buildTable() {
        table.clear();
        table.top().padTop(30);

        table.add(menuLabel).colspan(4).center();
        table.row().padTop(20);

        table.add(sortDropdown).colspan(4).center();
        table.row().padTop(20);

        // Headers
        table.add(new Label("Username", skin)).pad(10);
        table.add(new Label("Score", skin)).pad(10);
        table.add(new Label("Kills", skin)).pad(10);
        table.add(new Label("Max Alive Time", skin)).pad(10);
        table.row();

        // Rows
        int count = Math.min(users.size(), 10);
        for (int i = 0; i < count; i++) {
            User user = users.get(i);
            table.add(new Label(user.getUsername(), skin)).pad(10);
            table.add(new Label(String.valueOf(user.getScore()), skin)).pad(10);
            table.add(new Label(String.valueOf(user.getKillNumber()), skin)).pad(10);
            table.add(new Label(String.valueOf(user.getMaxAliveTime()), skin)).pad(10);
            table.row();
        }

        // Back button
        table.row().padTop(30);
        table.add(backButton).colspan(4).center();
    }


    private void refreshUserRows() {
        table.clear();

        table.top().padTop(30);
        table.add(menuLabel).colspan(4).center();
        table.row().padTop(20);

        table.add(sortDropdown).colspan(4).center();
        table.row().padTop(20);

        // Header
        table.add(new Label("Username", skin)).pad(10);
        table.add(new Label("Score", skin)).pad(10);
        table.add(new Label("Kills", skin)).pad(10);
        table.add(new Label("Max Alive Time", skin)).pad(10);
        table.row();

        // Rows
        int count = Math.min(users.size(), 10);
        for (int i = 0; i < count; i++) {
            User user = users.get(i);
            table.add(new Label(user.getUsername(), skin)).pad(10);
            table.add(new Label(String.valueOf(user.getScore()), skin)).pad(10);
            table.add(new Label(String.valueOf(user.getKillNumber()), skin)).pad(10);
            table.add(new Label(String.valueOf(user.getMaxAliveTime()), skin)).pad(10);
            table.row();
        }

        table.row().padTop(30);
        table.add(backButton).colspan(4).center();
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

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

    public TextButton getBackButton() {
        return backButton;
    }

    public SelectBox<String> getSortDropdown() {
        return sortDropdown;
    }
}
