package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.LoginMenuView;
import com.tildawn.Views.MainMenuView;
import com.tildawn.Views.StartMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }
    public void handleLoginMenuButtons(){
        if (view != null) {
            LoginMenuView loginMenuView2 = new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin());
            if (view.getLoginButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                String username = view.getUsernameButton().getText().trim();
                String password = view.getPasswordButton().getText().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    loginMenuView2.setErrorLabel("Username or Password is empty");
                    loginMenuView2.setSuccessLabel("");
                }
                else{
                    User user = findUserByUsername(username);
                    if (user == null) {
                        loginMenuView2.setErrorLabel("Username not found");
                        loginMenuView2.setSuccessLabel("");
                    }
                    else{
                        if (!user.getPassword().equals(password)) {
                            loginMenuView2.setErrorLabel("Wrong password");
                            loginMenuView2.setSuccessLabel("");
                        }
                        else{
                            App.setLoggedInUser(user);
                            Main.getMain().getScreen().dispose();
                            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                            return;
                        }
                    }
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(loginMenuView2);
            }
            else if (view.getGuestButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                User user = new User("guest", "guest", "guest");
                App.setLoggedInUser(user);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getChangePasswordButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                String username = view.getUsernameForgetButton().getText().trim();
                String password = view.getPasswordForgetButton().getText().trim();
                String answer = view.getSecurityQuestionForgetButton().getText().trim();

                if (username.isEmpty() || password.isEmpty() || answer.isEmpty()) {
                    loginMenuView2.setErrorLabel("Username or Password or Answer is empty");
                    loginMenuView2.setSuccessLabel("");
                }
                else{
                    User user = findUserByUsername(username);
                    if (user == null) {
                        loginMenuView2.setErrorLabel("Username not found");
                        loginMenuView2.setSuccessLabel("");
                    }
                    else{
                        if (!user.getSecurityQuestionAnswer().equals(answer)) {
                            loginMenuView2.setErrorLabel("Wrong answer");
                            loginMenuView2.setSuccessLabel("");
                        }
                        else{
                            if (!password.matches("(?=.*[A-Z])(?=.*[()&_@$#%*]).{8,}")){
                                loginMenuView2.setErrorLabel("Wrong password format");
                                loginMenuView2.setSuccessLabel("");
                            }
                            else{
                                user.setPassword(password);
                                loginMenuView2.setSuccessLabel("password changed successfully");
                                loginMenuView2.setErrorLabel("");
                            }
                        }
                    }
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(loginMenuView2);
            }
            else if (view.getExitMenuButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
    private User findUserByUsername(String username) {
        for (User user : App.getUsers()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
