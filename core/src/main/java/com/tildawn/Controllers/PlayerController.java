package com.tildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tildawn.Main;
import com.tildawn.Models.App;
import com.tildawn.Models.Player;

public class PlayerController {
    private Player player;

    public PlayerController(Player player){
        this.player = player;
    }


    public void update(int mapWidth, int mapHeight) {
        player.getPlayerSprite().draw(Main.getBatch());

        if(player.isPlayerIdle()){
            idleAnimation();
        }

        handlePlayerInput(mapWidth, mapHeight);
    }

    public void handlePlayerInput(int mapWidth, int mapHeight) {
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getDown())){
            player.setPosY(player.getPosY() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getLeft())){
            player.setPosX(player.getPosX() - player.getSpeed());
            player.getPlayerSprite().flip(true, false);
        }
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getUp())){
            player.setPosY(player.getPosY() + player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(App.getLoggedInUser().getRight())){
            player.setPosX(player.getPosX() + player.getSpeed());
        }

        float bgX = 0;
        float bgY = 0;
        float bgWidth = mapWidth;
        float bgHeight = mapHeight;

        float spriteWidth = player.getPlayerSprite().getWidth();
        float spriteHeight = player.getPlayerSprite().getHeight();

        float minX = bgX;
        float maxX = bgX + bgWidth - spriteWidth;

        float minY = bgY;
        float maxY = bgY + bgHeight - spriteHeight;

        if (player.getPosX() < minX) player.setPosX(minX);
        if (player.getPosX() > maxX) player.setPosX(maxX);
        if (player.getPosY() < minY) player.setPosY(minY);
        if (player.getPosY() > maxY) player.setPosY(maxY);

        player.getPlayerSprite().setPosition(player.getPosX(), player.getPosY());
    }

    public void idleAnimation(){
        Animation<Texture> animation = player.getHeroType().getIdleAnimation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }
}
