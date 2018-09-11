package games.metalmethod.orbhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import games.metalmethod.orbhive.controller.AssetLoader;
import games.metalmethod.orbhive.view.screens.GameScreen;

public class OrbHiveGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("OrbHiveGame", "created");

        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
