package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.tildawn.Main;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.RegisterMenuView;
import com.tildawn.Views.StartMenuView;

public class StartMenuController {
    private StartMenuView view;

    public void setView(StartMenuView view) {
        this.view = view;
    }

    public void handleStartMenuButtons(){
        if (view != null) {
            if (view.getRegisterButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
