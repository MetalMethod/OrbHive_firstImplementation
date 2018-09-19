package games.metalmethod.orbhive.view;

import games.metalmethod.orbhive.model.gameworld.GameWorld;
import games.metalmethod.orbhive.view.screens.GameScreen;

public class View {

    private GameRenderer gameRenderer;
    private GameWorld gameWorld;

    private int gameHeight;
    private int midPointY;

    private GameScreen gameScreen;

    public View(){

        gameRenderer = new GameRenderer(gameWorld, (int)gameHeight, midPointY);
        gameWorld = new GameWorld(midPointY);

        gameScreen = new GameScreen();
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
}
