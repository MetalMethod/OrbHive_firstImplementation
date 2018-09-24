package games.metalmethod.orbhive.view.screens;

import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.view.assets.TextureHandler;
import games.metalmethod.orbhive.view.input.InputHandler;

public class GameScreen extends View {

    public GameScreen(Controller controller){
        super(controller);

        // get gameWorld
        gameWorld = controller.getGameWorld();

        // get textureHandler
        textureHandler = new TextureHandler(controller, gameWorld, (int)gameHeight, midPointY);
        textureHandler.render(runTime);

        // Binds the inputHandler to the character
        setInput();
    }

    public int getMidPointY() {
        return midPointY;
    }

    @Override
    public void render(float delta) {
        controller.update(delta);

        runTime += delta;
        // gameWorld.update(delta);
        textureHandler.render(runTime);
    }

    public void setInput(){
        Gdx.input.setInputProcessor(new InputHandler(controller));
    }

}
