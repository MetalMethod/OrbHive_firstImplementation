package games.metalmethod.orbhive.view.screens;

import com.badlogic.gdx.Gdx;
import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.model.gameobjects.entities.BasicEnemy;
import games.metalmethod.orbhive.model.gameobjects.entities.Player;
import games.metalmethod.orbhive.view.assets.TextureHandler;
import games.metalmethod.orbhive.view.input.InputHandler;

public class GameScreen extends View {

    private Player player;
    private BasicEnemy singleBasicEnemy;
    private BasicEnemy waspBasicEnemy;

    public GameScreen(Controller controller){
        super(controller);
        player = controller.getPlayer();

        singleBasicEnemy = controller.createSingleEnemy();
        waspBasicEnemy = controller.createWaspEnemy();
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

    public void update(float delta){
        controller.detectPlayerCollisionEnemy(player, singleBasicEnemy);
        controller.detectPlayerCollisionEnemy(player, waspBasicEnemy);
    }
}
