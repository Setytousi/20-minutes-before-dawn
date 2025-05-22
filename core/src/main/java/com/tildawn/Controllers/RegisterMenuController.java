package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.tildawn.Main;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.RegisterMenuView;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if (view != null) {
            if (view.getRegisterButton().isChecked()){
                String username = view.getUsernameButton().getText();
                String password = view.getPasswordButton().getText();
                String answer = view.getSecurityQuestionButton().getText();




                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
