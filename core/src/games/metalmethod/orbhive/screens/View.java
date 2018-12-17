package games.metalmethod.orbhive.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import games.metalmethod.orbhive.Constants;
import games.metalmethod.orbhive.Controller;
import games.metalmethod.orbhive.assets.TextureHandler;

public abstract class View implements Screen {

    protected Controller controller;

    protected float runTime = 0;

    protected int midPointY;
    protected TextureHandler textureHandler;

    protected float screenWidth;
    protected float screenHeight;
    protected float gameWidth ;
    protected float gameHeight;

    public View(Controller controller) {
        this.controller = controller;

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameWidth = Constants.screenWidth;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        midPointY = (int) (gameHeight / 2);

    }

    @Override
    public void render(float delta) {

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
