package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.LoginMenuView;
import com.tildawn.Views.RegisterMenuView;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if (view != null) {
            RegisterMenuView registerMenuView2 = new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin());

            if (view.getRegisterButton().isChecked()){
                String username = view.getUsernameButton().getText().trim();
                String password = view.getPasswordButton().getText().trim();
                String answer = view.getSecurityQuestionButton().getText().trim();

                if (!username.isEmpty() && !password.isEmpty() && !answer.isEmpty() && password.matches("(?=.*[A-Z])(?=.*[()&_@$#%*]).{8,}")) {
                    User user = new User(username, password, answer);
                    App.addUser(user);
                    registerMenuView2.setSuccessLabel("registered successfully");
                    registerMenuView2.setErrorLabel("");
                }
                else {
                    registerMenuView2.setSuccessLabel("");
                    registerMenuView2.setErrorLabel("invalid input format");
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(registerMenuView2);
            }
            else if (view.getLoginButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
