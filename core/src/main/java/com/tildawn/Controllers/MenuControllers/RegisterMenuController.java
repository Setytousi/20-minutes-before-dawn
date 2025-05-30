package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Database;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.LoginMenuView;
import com.tildawn.Views.RegisterMenuView;

import javax.xml.crypto.Data;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if (view != null) {
            RegisterMenuView registerMenuView2 = new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin());

            if (view.getRegisterButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                String username = view.getUsernameButton().getText().trim();
                String password = view.getPasswordButton().getText().trim();
                String answer = view.getSecurityQuestionButton().getText().trim();

                if (!username.isEmpty() && !password.isEmpty() && !answer.isEmpty() && password.matches("(?=.*[A-Z])(?=.*[()&_@$#%*]).{8,}")) {
                    boolean check = false;
                    for (User user : App.getUsers()) {
                        if (user.getUsername().equals(username)) {
                            check = true;
                        }
                    }
                    if (check) {
                        registerMenuView2.setErrorLabel("Username is already taken!");
                        registerMenuView2.setSuccessLabel("");
                    }
                    else {
                        User user = new User(username, password, answer, 0, 0, 0);
                        Database.saveUser(username, password, answer, 0, 0, 0);
                        App.addUser(user);
                        registerMenuView2.setSuccessLabel("registered successfully");
                        registerMenuView2.setErrorLabel("");
                    }
                }
                else {
                    registerMenuView2.setSuccessLabel("");
                    registerMenuView2.setErrorLabel("invalid input format");
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(registerMenuView2);
            }
            else if (view.getLoginButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
