package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.MainMenuView;
import com.tildawn.Views.ProfileMenuView;
import com.tildawn.Views.RegisterMenuView;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleProfileMenuButtons() {
        if (view == null) return;

        if (view.getBackButton().isChecked()) {
            // ✅ Use dropdown avatar only if no custom avatar was uploaded
            if (!view.hasCustomAvatar()) {
                switch (view.getAvatarSelectBox().getSelectedIndex()) {
                    case 0:
                        App.getLoggedInUser().setAvatarTexture(GameAssetManager.getGameAssetManager().getAvatar1Texture());
                        App.getLoggedInUser().setAvatarPath(null);
                        break;
                    case 1:
                        App.getLoggedInUser().setAvatarTexture(GameAssetManager.getGameAssetManager().getAvatar2Texture());
                        App.getLoggedInUser().setAvatarPath(null);
                        break;
                    case 2:
                        App.getLoggedInUser().setAvatarTexture(GameAssetManager.getGameAssetManager().getAvatar3Texture());
                        App.getLoggedInUser().setAvatarPath(null);
                        break;
                }
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }

        else if (view.getChangePasswordButton().isChecked()) {
            ProfileMenuView view2 = new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin());
            String password = view.getNewPasswordTextField().getText().trim();

            if (password.isEmpty()) {
                view2.setErrorMessage("Password cannot be empty");
            } else if (!password.matches("(?=.*[A-Z])(?=.*[()&_@$#%*]).{8,}")) {
                view2.setErrorMessage("Password format is incorrect");
            } else {
                App.getLoggedInUser().setPassword(password);
                view2.setErrorMessage("Password changed successfully");
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(view2);
        }

        else if (view.getChangeUsernameButton().isChecked()) {
            ProfileMenuView view2 = new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin());
            String username = view.getNewUsernameTextField().getText().trim();
            if (username.isEmpty()) {
                view2.setErrorMessage("Username cannot be empty");
            }
            else {
                boolean check = false;
                for (User user : App.getUsers()) {
                    if (username.equals(user.getUsername())) {
                        check = true;
                    }
                }
                if (check) {
                    view2.setErrorMessage("Username is already taken!");
                }
                else{
                    App.getLoggedInUser().setUsername(username);
                    view2.setErrorMessage("Username changed successfully");
                }
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(view2);
        }

        else if (view.getDeleteAccountButton().isChecked()) {
            App.removeUser(App.getLoggedInUser());
            App.setLoggedInUser(null);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
