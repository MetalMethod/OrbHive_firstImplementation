package games.metalmethod.orbhive.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.math.Intersector;
import games.metalmethod.orbhive.model.gameobjects.Enemy;
import games.metalmethod.orbhive.model.gameobjects.EnemyFactory;
import games.metalmethod.orbhive.model.gameobjects.Player;
import games.metalmethod.orbhive.model.gameobjects.PlayerState;
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
    private Enemy waspEnemy;


    private float runTime;

    private float playerHitTime;

    @Override
    public void create() {
        Gdx.app.log("Controller", "created");

        AssetLoader.load();
        enemyFactory = new EnemyFactory();
        singleEnemy = enemyFactory.createEnemy();
        waspEnemy = enemyFactory.createEnemy();

        player = new Player(50, 110);
        playerHitTime = 500;

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);

        int midPointY = gameScreen.getMidPointY();
        gameWorld = new GameWorld(midPointY);
    }

    public void update(float delta) {
        runTime = gameScreen.getRunTime();
        player.update(delta);
        detectWalls();

        detectPlayerCollisionEnemy(player, singleEnemy);
        detectPlayerCollisionEnemy(player, waspEnemy);

        // create enemy after 3 secs
        singleEnemy.update(delta);
        waspEnemy.update(delta);

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

        player.setVelocity((Vector) player.getVelocity().add(0, -Constants.movementVelocity));
        isPlayerMoving = true;
    }

    public void movePlayerForward() {
        player.setVelocity((Vector) player.getVelocity().add(Constants.movementVelocity, 0));
        isPlayerMoving = true;
    }

    public void movePlayerDown() {
        player.setVelocity((Vector) player.getVelocity().add(0, Constants.movementVelocity));
        isPlayerMoving = true;
    }

    public void movePlayerBack() {
        player.setVelocity((Vector) player.getVelocity().add(-Constants.movementVelocity, 0));
        isPlayerMoving = true;
    }

    public void stopPlayer() {
        stopMovePlayerY();
        stopMovePlayerX();
    }

    public void stopMovePlayerY() {
        player.setVelocity((Vector) player.getVelocity().set(player.getVelocity().x, 0));
        isPlayerMoving = false;
    }

    public void stopMovePlayerX() {
        player.setVelocity((Vector) player.getVelocity().set(0, player.getVelocity().y));
        isPlayerMoving = false;
    }

    public void detectWalls() {
        //left
        if (player.getPosition().x < 5) {
            player.setPosition((Vector) player.getPosition().set(5, player.getPosition().y));
        }
        //right
        if (player.getPosition().x > 410) {
            player.setPosition((Vector) player.getPosition().set(410, player.getPosition().y));
        }
        //top
        if (player.getPosition().y < 0) {
            player.setPosition((Vector) player.getPosition().set(player.getPosition().x, 0));
        }
        //down
        if (player.getPosition().y > 220) {
            player.setPosition((Vector) player.getPosition().set(player.getPosition().x, 220));
        }
    }

    public boolean isPlayerMoving() {
        return isPlayerMoving;
    }


    public Enemy createSingleEnemy() {
        return singleEnemy;
    }

    public Enemy createWaspEnemy() {
        return waspEnemy;
    }

    public boolean detectPlayerCollisionEnemy(Player player, Enemy enemy){

        boolean result = Intersector.overlaps(player.getBoundingRectangle(), enemy.getBoundingBox());

        if(result){
            playerHitTime = 0;
            player.takeHit(Constants.playerHitAcceleration);
            enemy.takeHit();
        }else {
            playerHitTime++;

        }


        return result;
    }

    public boolean isPlayerHit() {

        if(playerHitTime < 50){
                return true;
            }else {
                return false;
            }
        }

    public PlayerState playerState(Player player) {

        if(player.getState() == PlayerState.FULL){
                return PlayerState.FULL;
        }

        if(player.getState() == PlayerState.MID){
            return PlayerState.MID;
        }

        if(player.getState() == PlayerState.LAST){
            return PlayerState.LAST;
        }

//        if(player.getState() == PlayerState.DEAD){
//            return PlayerState.DEAD;
//        }

        return PlayerState.DEAD;

    }
}
