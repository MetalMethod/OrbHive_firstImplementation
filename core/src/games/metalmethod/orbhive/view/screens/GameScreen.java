package games.metalmethod.orbhive.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import games.metalmethod.orbhive.view.assets.TextureHandler;
import games.metalmethod.orbhive.model.gameworld.GameWorld;
import games.metalmethod.orbhive.view.input.InputHandler;

public class GameScreen implements Screen {

    private float runTime = 0;
    private TextureHandler textureHandler;
    private GameWorld gameWorld;

    public GameScreen(){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 455;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        gameWorld = new GameWorld(midPointY);
        textureHandler = new TextureHandler(gameWorld, (int)gameHeight, midPointY);

        // Binds the inputHandler to the character
        Gdx.input.setInputProcessor(new InputHandler(gameWorld));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
       // gameWorld.update(delta);
        textureHandler.render(runTime);
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
