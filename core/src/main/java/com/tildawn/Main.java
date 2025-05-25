package com.tildawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tildawn.Controllers.MenuControllers.StartMenuController;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Views.ProfileMenuView;
import com.tildawn.Views.StartMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }

    public void onFileDropped(String path) {
        if (getScreen() instanceof ProfileMenuView) {
            ((ProfileMenuView) getScreen()).onAvatarFileDropped(path);
        }
    }

}
