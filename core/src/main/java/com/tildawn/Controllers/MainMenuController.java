package com.tildawn.Controllers;

import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.MainMenuView;
import com.tildawn.Views.StartMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }


    public void handleMainMenuButtons(){
        if (view != null) {
            if (view.getLogout().isChecked()){
                if (!App.getLoggedInUser().getUsername().equals("guest")) {
                    App.getLoggedInUser().setSavedGame(App.getCurrentGame());
                }
                App.setLoggedInUser(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getOptions().isChecked()){
                //TODO
            }
            else if (view.getProfile().isChecked()){
                //TODO
            }
            else if (view.getScoreBoard().isChecked()){
                //TODO
            }
            else if (view.getTalentMenu().isChecked()){
                //TODO
            }
            else if (view.getNewGame().isChecked()){
                //TODO
            }
            else if (view.getResumeGame().isChecked()){
                //TODO
            }
        }
    }
}
