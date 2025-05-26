package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Main;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.LoginMenuView;
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
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getLoginButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
