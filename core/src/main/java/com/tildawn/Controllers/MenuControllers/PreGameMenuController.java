package com.tildawn.Controllers.MenuControllers;

import com.tildawn.Controllers.GameController;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.Enums.Heros;
import com.tildawn.Models.Enums.Weapons;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.GameView;
import com.tildawn.Views.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;

    public void setView(PreGameMenuView view){
        this.view = view;
    }



    public void handlePreGameMenuButtons(){
        if (view != null) {
            if (view.getHero1().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setHeroType(Heros.hero1);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getHero2().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setHeroType(Heros.hero2);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getHero3().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setHeroType(Heros.hero3);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getHero4().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setHeroType(Heros.hero4);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getHero5().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setHeroType(Heros.hero5);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getWeapon1().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setWeaponType(Weapons.weapon1);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getWeapon2().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setWeaponType(Weapons.weapon2);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getWeapon3().isPressed()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                App.getLoggedInUser().setWeaponType(Weapons.weapon3);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            else if (view.getStartGameButton().isChecked()){
                GameAssetManager.getGameAssetManager().getUIclick().setVolume(0.5f);
                GameAssetManager.getGameAssetManager().getUIclick().play();
                if (view.getGameTimeSelectBox().getSelectedIndex() == 0){
                    App.getLoggedInUser().setGameTime(2);
                }
                else if (view.getGameTimeSelectBox().getSelectedIndex() == 1){
                    App.getLoggedInUser().setGameTime(5);
                }
                else if (view.getGameTimeSelectBox().getSelectedIndex() == 2){
                    App.getLoggedInUser().setGameTime(10);
                }
                else if (view.getGameTimeSelectBox().getSelectedIndex() == 3){
                    App.getLoggedInUser().setGameTime(20);
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
