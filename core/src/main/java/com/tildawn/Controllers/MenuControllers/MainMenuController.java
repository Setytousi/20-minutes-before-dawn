package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.Game;
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
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                if (!App.getLoggedInUser().getUsername().equals("guest")) {
                    App.getLoggedInUser().setSavedGame(App.getCurrentGame());
                }
                App.setLoggedInUser(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getOptions().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new OptionMenuView(new OptionMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getProfile().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getScoreBoard().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreBoardMenuView(new ScoreBoardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getTalentMenu().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new HintMenuView(GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getNewGame().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getResumeGame().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                //TODO
            }
        }
    }
}
