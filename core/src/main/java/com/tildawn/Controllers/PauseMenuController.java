package com.tildawn.Controllers;

import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.LoseMenuView;
import com.tildawn.Views.PauseMenuView;

public class PauseMenuController {
    private PauseMenuView view;

    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public void handlePauseMenuButtons() {
        if (view.getResumeButton().isChecked()){
            User user = App.getLoggedInUser();
            user.setBlackAndWhite(view.getBlackAndWhiteSelectBox().getSelectedIndex() == 0);
            if (user.isBlackAndWhite()){
                Main.getBatch().setShader(Main.getGrayscaleShader());
            } else {
                Main.getBatch().setShader(null);
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(view.getGameController().getView());
        }
        else if (view.getSaveAndExitButton().isChecked()){
            //TODO
        }
        else if (view.getGiveUpButton().isChecked()){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoseMenuView(view.getGameController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
