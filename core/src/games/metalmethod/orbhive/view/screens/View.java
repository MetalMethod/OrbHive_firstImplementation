package games.metalmethod.orbhive.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.model.gameworld.GameWorld;
import games.metalmethod.orbhive.view.assets.TextureHandler;
import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.view.input.InputHandler;

public abstract class View implements Screen {

    protected Controller controller;

    protected float runTime = 0;

    protected int midPointY;
    protected TextureHandler textureHandler;
    protected GameWorld gameWorld;

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

        // get gameWorld
        gameWorld = controller.getGameWorld();

        // get textureHandler
        textureHandler = new TextureHandler(controller, gameWorld, (int)gameHeight, midPointY);
        textureHandler.render(runTime);

        // Binds the inputHandler to the character
        setInput();
    }

    public void setInput(){
        Gdx.input.setInputProcessor(new InputHandler(controller));
    }

    @Override
    public void render(float delta) {
        controller.update(delta);

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
        textureHandler.dispose();
    }

}
