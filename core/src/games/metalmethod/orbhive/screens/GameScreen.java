package games.metalmethod.orbhive.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import games.metalmethod.orbhive.gameworld.GameRenderer;
import games.metalmethod.orbhive.gameworld.GameWorld;
import games.metalmethod.orbhive.helpers.InputHandler;

public class GameScreen implements Screen {

    private float runTime = 0;
    private GameRenderer gameRenderer;
    private GameWorld gameWorld;

    public GameScreen(){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 455;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        gameWorld = new GameWorld(midPointY);
        gameRenderer = new GameRenderer(gameWorld, (int)gameHeight, midPointY);

        // Binds the inputHandler to the character
        Gdx.input.setInputProcessor(new InputHandler(gameWorld));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
       // gameWorld.update(delta);
        gameRenderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
    }
}
