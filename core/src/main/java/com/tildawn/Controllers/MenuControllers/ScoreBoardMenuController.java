package com.tildawn.Controllers;

import com.tildawn.Main;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.MainMenuView;
import com.tildawn.Views.ScoreBoardMenuView;

public class ScoreBoardMenuController {
    private ScoreBoardMenuView view;

    public void setView(ScoreBoardMenuView view) {
        this.view = view;
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

}
