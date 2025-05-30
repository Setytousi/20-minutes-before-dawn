package com.tildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controllers.GameController;
import com.tildawn.Main;
import com.tildawn.Models.App;

import java.util.Random;

public class ChooseAbilityView implements Screen {
    private Stage stage;
    private Table table;
    private TextButton ability1Button;
    private TextButton ability2Button;
    private TextButton ability3Button;
    private int ability1;
    private int ability2;
    private int ability3;
    private Label chooseAbilityLabel;
    private GameView gameView;

    public ChooseAbilityView(Skin skin, GameView gameView) {
        this.gameView = gameView;
        table = new Table(skin);
        Random rand = new Random();
        ability1 = rand.nextInt(2);
        ability2 = rand.nextInt(2) + 2;
        ability3 = 4;
        ability1Button = new TextButton(ability1 == 0 ? "VITALITY (max Hp increses by 1)" : "DAMAGER (adds to weapon damage for 10 secs)", skin);
        ability2Button = new TextButton(ability2 == 2 ? "PROCREASE (adds to weapons projectile)" : "AMOCREASE (increases max ammo by 5)", skin);
        ability3Button = new TextButton("SPEEDY (increases speed)", skin);
        chooseAbilityLabel = new Label("Choose Ability", skin, "title");
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(chooseAbilityLabel);
        table.row().pad(20, 0, 0, 0);
        table.add(ability1Button);
        table.row().pad(20, 0, 0, 0);
        table.add(ability2Button);
        table.row().pad(20, 0, 0, 0);
        table.add(ability3Button);
        table.row().pad(20, 0, 0, 0);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        handleButtons();
    }

    private void handleButtons() {
        if (ability1Button.isChecked()) {
            if (ability1 == 0){
                gameView.getController().getPlayerController().getPlayer().setMaxHp(
                    gameView.getController().getPlayerController().getPlayer().getMaxHp() + 1
                );
            }
            else {
                gameView.getController().getPlayerController().setDamager(true);
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(gameView);
        }
        else if (ability2Button.isChecked()) {
            if (ability2 == 2){
                gameView.getController().getPlayerController().getPlayer().setProjectileAdded(
                    gameView.getController().getPlayerController().getPlayer().getProjectileAdded() + 1
                );
            }
            else {
                gameView.getController().getPlayerController().getPlayer().setAmmoMaxAdded(
                    gameView.getController().getPlayerController().getPlayer().getAmmoMaxAdded() + 5
                );
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(gameView);
        }
        else if (ability3Button.isChecked()) {
            gameView.getController().getPlayerController().setSpeedy(true);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(gameView);
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
