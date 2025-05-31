package com.tildawn.Models;

import com.tildawn.Controllers.GameController;
import com.tildawn.Views.GameView;

public class Game {
    private GameView view;
    private GameController gameController;

    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
