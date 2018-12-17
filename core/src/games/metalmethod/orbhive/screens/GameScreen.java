package games.metalmethod.orbhive.screens;

import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.Controller;
import games.metalmethod.orbhive.assets.TextureHandler;
import games.metalmethod.orbhive.input.InputHandler;

public class GameScreen extends View {

    public GameScreen(Controller controller){
        super(controller);

        // get textureHandler
        textureHandler = new TextureHandler(controller, (int)gameHeight, midPointY);
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

        textureHandler.render(runTime);
    }

    public void setInput(){
        Gdx.input.setInputProcessor(new InputHandler(controller));
    }

    public  float getRunTime(){
        return runTime;
    }
}
