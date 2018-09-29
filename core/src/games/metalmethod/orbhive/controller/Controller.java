package games.metalmethod.orbhive.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import games.metalmethod.orbhive.model.gameobjects.Enemy;
import games.metalmethod.orbhive.model.gameobjects.EnemyFactory;
import games.metalmethod.orbhive.model.gameobjects.Player;
import games.metalmethod.orbhive.view.assets.AssetLoader;
import games.metalmethod.orbhive.view.interfaces.Vector;
import games.metalmethod.orbhive.view.screens.GameScreen;
import games.metalmethod.orbhive.model.gameworld.GameWorld;
import games.metalmethod.orbhive.model.Constants;

public class Controller extends Game {

    private GameWorld gameWorld;
    private GameScreen gameScreen;

    private Player player;
    private boolean isPlayerMoving = false;

    private EnemyFactory enemyFactory;
    private Enemy singleEnemy;

    private float runTime;

    @Override
    public void create() {
        Gdx.app.log("Controller", "created");

        AssetLoader.load();
        enemyFactory = new EnemyFactory();
        singleEnemy = enemyFactory.createSimpleEnemy();

        player = new Player(50, 110);

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);


        int midPointY = gameScreen.getMidPointY();
        gameWorld = new GameWorld(midPointY);


    }

    public void update(float delta) {
        runTime = gameScreen.getRunTime();
        player.update(delta);
        detectWalls();

        // create enemy after 3 secs
        singleEnemy.update(delta);

    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public Player getPlayer() {
        return player;
    }

    public void movePlayerUp() {
        player.setVelocity((Vector)player.getVelocity().add(0, -Constants.movementVelocity));
        isPlayerMoving = true;
    }

    public void movePlayerForward() {
        player.setVelocity((Vector)player.getVelocity().add(Constants.movementVelocity, 0));
        isPlayerMoving = true;

    }

    public void movePlayerDown() {
        player.setVelocity((Vector) player.getVelocity().add(0, Constants.movementVelocity));
        isPlayerMoving = true;

    }

    public void movePlayerBack() {
        player.setVelocity((Vector)player.getVelocity().add(-Constants.movementVelocity, 0));
        isPlayerMoving = true;

    }

    public void stopPlayer() {
        player.setVelocity((Vector)player.getVelocity().set(0, 0));
        isPlayerMoving = false;
    }

    public void stopMovePlayerY() {
        player.setVelocity((Vector)player.getVelocity().set(player.getVelocity().x, 0));
        isPlayerMoving = false;
    }

    public void stopMovePlayerX() {
        player.setVelocity((Vector)player.getVelocity().set(0, player.getVelocity().y));
        isPlayerMoving = false;
    }

    public void detectWalls(){
        //left
        if(player.getPosition().x < 5  ){
            player.setPosition((Vector)player.getPosition().set(5, player.getPosition().y));
        }
        //right
        if(player.getPosition().x > 410 ){
            player.setPosition((Vector)player.getPosition().set(410, player.getPosition().y));
        }
        //top
        if(player.getPosition().y < 0 ){
            player.setPosition((Vector)player.getPosition().set(player.getPosition().x, 0));
        }
        //down
        if(player.getPosition().y > 220){
            player.setPosition((Vector)player.getPosition().set(player.getPosition().x, 220));
        }
    }

    public boolean isPlayerMoving() {
        return isPlayerMoving;
    }

    public boolean isPlayerDying() {
        return false;
    }

    public Enemy createEnemy(){
        return singleEnemy;
    }

}
