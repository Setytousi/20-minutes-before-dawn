package com.tildawn.Controllers;

import com.badlogic.gdx.Input;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.MainMenuView;
import com.tildawn.Views.OptionMenuView;

public class OptionMenuController {
    private OptionMenuView view;

    public void setView(OptionMenuView view){
        this.view = view;
    }
    public void handleOptionMenuButtons(){
        if (view != null) {
            if (view.getBackButton().isChecked()){
                User user = App.getLoggedInUser();
                user.setSoundVolume(view.getVolumeSlider().getValue());
                user.setMusic(view.getMusicSelectBox().getSelectedIndex());
                user.setSfxEnabled(view.getSfxSelectBox().getSelectedIndex() == 0);
                if (view.getControlsSelectBox().getSelectedIndex() == 0) {
                    user.setLeft(Input.Keys.A);
                    user.setRight(Input.Keys.D);
                    user.setUp(Input.Keys.W);
                    user.setDown(Input.Keys.S);
                }
                else{
                    user.setLeft(Input.Keys.RIGHT);
                    user.setRight(Input.Keys.LEFT);
                    user.setUp(Input.Keys.UP);
                    user.setDown(Input.Keys.DOWN);
                }
                user.setAutoReload(view.getAutoReloadSelectBox().getSelectedIndex() == 0);
                user.setBlackAndWhite(view.getBlackAndWhiteSelectBox().getSelectedIndex() == 0);

                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
