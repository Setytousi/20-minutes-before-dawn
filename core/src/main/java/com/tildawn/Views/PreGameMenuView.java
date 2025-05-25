package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.MenuControllers.PreGameMenuController;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.Enums.Heros;
import com.tildawn.Models.Enums.Weapons;


public class PreGameMenuView implements Screen {
    private PreGameMenuController controller;
    private Stage stage;
    private Table table;
    private Image currentHeroImage;
    private Image currentWeaponImage;
    private final Label currentHeroLabel;
    private final Label currentWeaponLabel;
    private final Label chooseHeroLabel;
    private final Label chooseWeaponLabel;
    private final Label gameTimeLabel;
    private ImageButton hero1, hero2, hero3, hero4, hero5;
    private ImageButton weapon1, weapon2, weapon3;
    private TextButton startGameButton;
    private final SelectBox<String> gameTimeSelectBox;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);

        currentHeroImage = new Image(App.getLoggedInUser().getHeroType().getTexture());
        currentWeaponImage = new Image(App.getLoggedInUser().getWeaponType().getTexture());
        currentHeroLabel = new Label("current hero", skin, "subtitle");
        currentWeaponLabel = new Label("current weapon", skin, "subtitle");
        chooseHeroLabel = new Label("choose hero", skin, "subtitle");
        chooseWeaponLabel = new Label("choose weapon", skin, "subtitle");
        table = new Table();
        hero1 = new ImageButton(skin);
        hero2 = new ImageButton(skin);
        hero3 = new ImageButton(skin);
        hero4 = new ImageButton(skin);
        hero5 = new ImageButton(skin);
        Image hero1Image = new Image(Heros.hero1.getTexture());
        Image hero2Image = new Image(Heros.hero2.getTexture());
        Image hero3Image = new Image(Heros.hero3.getTexture());
        Image hero4Image = new Image(Heros.hero4.getTexture());
        Image hero5Image = new Image(Heros.hero5.getTexture());
        Label hero1Label = new Label(Heros.hero1.getHeroName(), skin);
        Label hero2Label = new Label(Heros.hero2.getHeroName(), skin);
        Label hero3Label = new Label(Heros.hero3.getHeroName(), skin);
        Label hero4Label = new Label(Heros.hero4.getHeroName(), skin);
        Label hero5Label = new Label(Heros.hero5.getHeroName(), skin);
        hero1.add(hero1Image).width(90).height(90).padRight(60).padBottom(5).row();
        hero2.add(hero2Image).width(90).height(90).padRight(60).padBottom(5).row();
        hero3.add(hero3Image).width(90).height(90).padRight(60).padBottom(5).row();
        hero4.add(hero4Image).width(90).height(90).padRight(60).padBottom(5).row();
        hero5.add(hero5Image).width(90).height(90).padRight(60).padBottom(5).row();
        hero1.add(hero1Label).padLeft(50);
        hero2.add(hero2Label).padLeft(50);
        hero3.add(hero3Label).padLeft(50);
        hero4.add(hero4Label).padLeft(50);
        hero5.add(hero5Label).padLeft(50);
        weapon1 = new ImageButton(skin);
        weapon2 = new ImageButton(skin);
        weapon3 = new ImageButton(skin);
        Image weapon1Image = new Image(Weapons.weapon1.getTexture());
        Image weapon2Image = new Image(Weapons.weapon2.getTexture());
        Image weapon3Image = new Image(Weapons.weapon3.getTexture());
        Label weapon1Label = new Label(Weapons.weapon1.getName(), skin);
        Label weapon2Label = new Label(Weapons.weapon2.getName(), skin);
        Label weapon3Label = new Label(Weapons.weapon3.getName(), skin);
        weapon1.add(weapon1Image).width(90).height(90).center().padBottom(5).row();
        weapon2.add(weapon2Image).width(90).height(90).center().padBottom(5).row();
        weapon3.add(weapon3Image).width(90).height(90).center().padBottom(5).row();
        weapon1.add(weapon1Label).center();
        weapon2.add(weapon2Label).center();
        weapon3.add(weapon3Label).center();
        startGameButton = new TextButton("Start Game", skin);
        gameTimeSelectBox = new SelectBox<>(skin);
        gameTimeSelectBox.setItems("2 m", "5 m", "10 m", "20 m");
        if (App.getLoggedInUser().getGameTime() == 2){
            gameTimeSelectBox.setSelectedIndex(0);
        }
        else if (App.getLoggedInUser().getGameTime() == 5){
            gameTimeSelectBox.setSelectedIndex(1);
        }
        else if (App.getLoggedInUser().getGameTime() == 10){
            gameTimeSelectBox.setSelectedIndex(2);
        }
        else{
            gameTimeSelectBox.setSelectedIndex(3);
        }
        gameTimeLabel = new Label("Choose Game Time", skin, "subtitle");
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(currentHeroLabel);
        table.add(currentHeroImage).height(100).width(100).padRight(20);

        table.row().pad(30, 0, 0, 0);

        table.add(chooseHeroLabel);
        table.add(hero1).height(180).width(180).padRight(5);
        table.add(hero2).height(180).width(180).padRight(5);
        table.add(hero3).height(180).width(180).padRight(5);
        table.add(hero4).height(180).width(180).padRight(5);
        table.add(hero5).height(180).width(180).padRight(5);
        table.row().pad(30, 0, 0, 0);

        table.add(currentWeaponLabel);
        table.add(currentWeaponImage).height(100).width(100).padRight(20);

        table.row().pad(30, 0, 0, 0);

        table.add(chooseWeaponLabel);
        table.add(weapon1).height(180).width(180).padRight(5);
        table.add(weapon2).height(180).width(180).padRight(5);
        table.add(weapon3).height(180).width(180).padRight(5);

        table.row().pad(30, 0, 0, 0);
        table.add(gameTimeLabel).colspan(2).width(300).height(60).padRight(50);
        table.add(gameTimeSelectBox).colspan(3).width(200).height(60);
        table.row().pad(30, 0, 0, 0);
        table.add(startGameButton).height(100).width(400).center();


        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePreGameMenuButtons();
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

    public ImageButton getHero1() {
        return hero1;
    }

    public ImageButton getHero2() {
        return hero2;
    }

    public ImageButton getHero3() {
        return hero3;
    }

    public ImageButton getHero4() {
        return hero4;
    }

    public ImageButton getHero5() {
        return hero5;
    }

    public ImageButton getWeapon1() {
        return weapon1;
    }

    public ImageButton getWeapon2() {
        return weapon2;
    }

    public ImageButton getWeapon3() {
        return weapon3;
    }

    public TextButton getStartGameButton() {
        return startGameButton;
    }

    public SelectBox<String> getGameTimeSelectBox() {
        return gameTimeSelectBox;
    }
}
