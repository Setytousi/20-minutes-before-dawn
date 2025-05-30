package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.MenuControllers.MainMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.Enums.Heros;
import com.tildawn.Models.GameAssetManager;

public class HintMenuView implements Screen{
    private Stage stage;
    private Table table;
    private Label hero1;
    private Label hero2;
    private Label hero3;
    private Image hero1Button;
    private Image hero2Button;
    private Image hero3Button;
    private Label keys;
    private Label cheat1;
    private Label cheat2;
    private Label cheat3;
    private Label cheat4;
    private Label cheat5;
    private Label cheatCode;
    private Label description;
    private TextButton backButton;

    public HintMenuView(Skin skin) {
        table = new Table();
        hero1 = new Label(Heros.hero1.getHeroName() + "   max HP: " + Heros.hero1.getHP() + "   Speed: " + Heros.hero1.getSpeed(), skin, "subtitle");
        hero2 = new Label(Heros.hero2.getHeroName() + "   max HP: " + Heros.hero2.getHP() + "   Speed: " + Heros.hero2.getSpeed(), skin, "subtitle");
        hero3 = new Label(Heros.hero3.getHeroName() + "   max HP: " + Heros.hero3.getHP() + "   Speed: " + Heros.hero3.getSpeed(), skin, "subtitle");
        hero1Button = new Image(Heros.hero1.getTexture());
        hero2Button = new Image(Heros.hero2.getTexture());
        hero3Button = new Image(Heros.hero3.getTexture());
        keys = new Label("Keys: " + "WASD", skin, "subtitle");
        if (App.getLoggedInUser().getDown() == Input.Keys.S) keys = new Label("Keys: " + "ArrowKeys", skin, "subtitle");
        cheatCode = new Label("Cheat Codes:", skin, "subtitle");
        cheat1 = new Label("T : adds 1 minute to elapsed time", skin);
        cheat2 = new Label("L : increases player level", skin);
        cheat3 = new Label("H : adds health if health = 0", skin);
        cheat4 = new Label("F : gives you 1000 ammo", skin);
        cheat5 = new Label("B : boss fight", skin);
        description = new Label("Survive for the selected time to win", skin, "subtitle");
        backButton = new TextButton("Back", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(hero1Button).height(70).width(70).right();
        table.add(hero2Button).height(70).width(70).left();
        table.add(hero3Button).height(70).width(70).left();
        table.row();
        table.add(hero1);
        table.row();
        table.add(hero2);
        table.row();
        table.add(hero3);

        table.row().pad(10, 0, 0, 0);

        table.add(keys);
        table.row().pad(10, 0, 0, 0);

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

        table.add(description);
        table.row().pad(5, 0, 0, 0);
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
        if (backButton.isChecked()){
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
