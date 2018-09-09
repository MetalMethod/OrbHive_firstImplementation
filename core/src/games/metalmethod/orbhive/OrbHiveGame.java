package games.metalmethod.orbhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import games.metalmethod.orbhive.helpers.AssetLoader;
import games.metalmethod.orbhive.screens.GameScreen;

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
