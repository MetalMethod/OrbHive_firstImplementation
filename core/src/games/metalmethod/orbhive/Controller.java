package games.metalmethod.orbhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.math.Intersector;
import games.metalmethod.orbhive.gameobjects.entities.Enemy;
import games.metalmethod.orbhive.gameobjects.EnemyFactory;
import games.metalmethod.orbhive.gameobjects.entities.EntityState;
import games.metalmethod.orbhive.gameobjects.entities.Player;
import games.metalmethod.orbhive.assets.AssetLoader;
import games.metalmethod.orbhive.helpers.Vector;
import games.metalmethod.orbhive.screens.GameScreen;

import static games.metalmethod.orbhive.Constants.*;


public class Controller extends Game {

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
        // Gdx.app.log("Controller", "created");

        AssetLoader.load();
        enemyFactory = new EnemyFactory();
        singleEnemy = enemyFactory.createEnemy();
        waspEnemy = enemyFactory.createEnemy();

        player = new Player(Constants.playerWidth, Constants.playerHeight, new Vector(50, 110));
        playerHitTime = 500;

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);

        int midPointY = gameScreen.getMidPointY();
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
        if (player.getPosition().x < screenPaddingLeft) {
            player.setPosition((Vector) player.getPosition().set(screenPaddingLeft, player.getPosition().y));
        }
        //right
        if (player.getPosition().x > screenWidth - screenPaddingRight) {
            player.setPosition((Vector) player.getPosition().set(screenWidth - screenPaddingRight, player.getPosition().y));
        }
        //top
        if (player.getPosition().y < screenPaddingTop) {
            player.setPosition((Vector) player.getPosition().set(player.getPosition().x, screenPaddingTop));
        }
        //down
        if (player.getPosition().y > screenHeight - screenPaddingBottom) {
            player.setPosition((Vector) player.getPosition().set(player.getPosition().x, screenHeight - screenPaddingBottom));
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

        boolean result = Intersector.overlaps(player.getBoundingRectangle(), enemy.getBoundingRectangle());

        if(result){
            playerHitTime = 0;
            player.takeHit(Constants.playerHitAcceleration);
            enemy.takeHit(Constants.enemyHitAcceleration);
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

    public EntityState playerState(Player player) {

        if(player.getState() == EntityState.FULL){
                return EntityState.FULL;
        }

        if(player.getState() == EntityState.MID){
            return EntityState.MID;
        }

        if(player.getState() == EntityState.LAST){
            return EntityState.LAST;
        }

//        if(player.getState() == EntityState.DEAD){
//            return EntityState.DEAD;
//        }

        return EntityState.DEAD;
    }

    public void playerShoot() {
        player.shoot();
    }

    public boolean isPlayerShooting() {
        return false;
    }
}
