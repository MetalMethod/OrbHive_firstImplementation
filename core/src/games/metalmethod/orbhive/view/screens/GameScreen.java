package games.metalmethod.orbhive.view.screens;

import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.view.assets.TextureHandler;
import games.metalmethod.orbhive.view.input.InputHandler;

public class GameScreen extends View {

    public GameScreen(Controller controller){
        super(controller);
    }

    public int getMidPointY() {
        return midPointY;
    }


    public  float getRunTime(){
        return runTime;
    }

    @Override
    public void dispose() {
       super.dispose();
    }
}
