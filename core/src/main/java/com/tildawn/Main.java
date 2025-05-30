package com.tildawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tildawn.Controllers.MenuControllers.StartMenuController;
import com.tildawn.Models.App;
import com.tildawn.Models.GameAssetManager;
import com.tildawn.Models.User;
import com.tildawn.Views.ProfileMenuView;
import com.tildawn.Views.StartMenuView;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.Gdx;

import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private static Main main;
    private static SpriteBatch batch;
    private static ShaderProgram grayscaleShader;


    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        GameAssetManager.getGameAssetManager().getBackgroundMusic().setLooping(true);
        GameAssetManager.getGameAssetManager().getBackgroundMusic().setVolume(0.5f);
        GameAssetManager.getGameAssetManager().getBackgroundMusic().play();
        ShaderProgram.pedantic = false;
        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("shaders/default.vert"),
            Gdx.files.internal("shaders/grayscale.frag")
        );
        Main.setGrayscaleShader(grayscaleShader);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Crosshair);
        Database.createUsersTable();
        List<User> allUsers = Database.loadAllUsers();
        for (User u : allUsers) {
            App.addUser(u);
        }

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

    public static ShaderProgram getGrayscaleShader() {
        return grayscaleShader;
    }

    public static void setGrayscaleShader(ShaderProgram shader) {
        grayscaleShader = shader;
    }

}
