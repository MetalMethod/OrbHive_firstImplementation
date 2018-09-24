package games.metalmethod.orbhive.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.model.gameobjects.Player;
import games.metalmethod.orbhive.view.assets.AssetLoader;

import games.metalmethod.orbhive.view.screens.GameScreen;

import games.metalmethod.orbhive.model.gameworld.GameWorld;

import games.metalmethod.orbhive.model.Constants;

public class Controller extends Game {

    private GameWorld gameWorld;
    private GameScreen gameScreen;

    private Player player;

    @Override
    public void create() {
        Gdx.app.log("Controller", "created");

        AssetLoader.load();

        player = new Player(50, 50, Constants.playerSize, Constants.playerSize);

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);

        int midPointY = gameScreen.getMidPointY();
        gameWorld = new GameWorld(midPointY);
    }

    public void update(float delta) {
        player.update(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public Player getPlayer() {
        return player;
    }

    public void movePlayerUp() {
        player.setVelocity(player.getVelocity().add(0, -Constants.movementVelocity));
    }

    public void movePlayerForward() {
        player.setVelocity(player.getVelocity().add(Constants.movementVelocity, 0));
    }

    public void movePlayerDown() {
        player.setVelocity(player.getVelocity().add(0, Constants.movementVelocity));
    }

    public void movePlayerBack() {
        player.setVelocity(player.getVelocity().add(-Constants.movementVelocity, 0));
    }

    public void stopPlayer() {
        player.setVelocity(player.getVelocity().set(0, 0));
    }

    public void stopMovePlayerY() {
        player.setVelocity(player.getVelocity().set(player.getVelocity().x, 0));
    }

    public void stopMovePlayerX() {
        player.setVelocity(player.getVelocity().set(0, player.getVelocity().y));
    }

}
