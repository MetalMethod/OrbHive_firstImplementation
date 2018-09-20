package games.metalmethod.orbhive.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.view.assets.AssetLoader;

import games.metalmethod.orbhive.view.screens.GameScreen;

import games.metalmethod.orbhive.model.gameworld.GameWorld;

public class Controller extends Game {

    private GameWorld gameWorld;
    private GameScreen gameScreen;


    @Override
    public void create() {
        Gdx.app.log("Controller", "created");

        AssetLoader.load();

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);

        int midPointY = gameScreen.getMidPointY();
        gameWorld = new GameWorld(midPointY);
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
}
