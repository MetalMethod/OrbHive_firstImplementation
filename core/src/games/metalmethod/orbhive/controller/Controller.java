package games.metalmethod.orbhive.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import games.metalmethod.orbhive.view.AssetLoader;
import games.metalmethod.orbhive.view.screens.GameScreen;

public class Controller extends Game {

    @Override
    public void create() {
        Gdx.app.log("Controller", "created");

        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
