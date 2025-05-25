package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.*;

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
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new OptionMenuView(new OptionMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getProfile().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getScoreBoard().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreBoardMenuView(new ScoreBoardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getTalentMenu().isChecked()){
                //TODO
            }
            else if (view.getNewGame().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getResumeGame().isChecked()){
                //TODO
            }
        }
    }
}
