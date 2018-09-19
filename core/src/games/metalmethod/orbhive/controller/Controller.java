package games.metalmethod.orbhive.controller;

import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.model.gameworld.GameWorld;
import games.metalmethod.orbhive.view.GameRenderer;

public class Controller {

    private InputHandler inputHandler;

    public Controller(){
        //this.inputHandler = inputHandler;

//        gameWorld = new GameWorld(midPointY);

        // Binds the inputHandler to the character

    }

    public static void loadAssets(){
        AssetLoader.load();
    }

    public static void disposeAssets(){
        AssetLoader.dispose();
    }

    public static void onPlayerKeyForward(){

    }

}
