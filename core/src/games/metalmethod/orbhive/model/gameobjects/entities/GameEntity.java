package games.metalmethod.orbhive.model.gameobjects.entities;

import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.view.interfaces.BaseRectangle;
import games.metalmethod.orbhive.view.interfaces.Vector;

/**
 * Abstract class for all game entities that can be controlled.
 * made to abstract Player, BasicEnemy
 */
public abstract class GameEntity {

    protected Vector position;
    protected Vector velocity;
    protected Vector acceleration;

    /**
     * positive change in rotation is a clockwise rotation and that a negative change in rotation is a counterclockwise rotation.
     */
    protected float rotation;

    protected int width;
    protected int height;


    /**
     * Object for collision detection
     */
    protected BaseRectangle boundingRectangle;

    protected EntityState currentState;
    protected int lifes;

    /***
     *
     * @param width
     * @param height
     * @param position
     */
    public GameEntity(int width, int height, Vector position) {
        this.width = width;
        this.height = height;

        this.position = position;
        velocity = new Vector(0, 0);
        acceleration = new Vector(0,0);


        boundingRectangle = new BaseRectangle();

        this.currentState = EntityState.FULL;
        this.lifes = Constants.initialEntityLives;
    }

    /***
     *r
     * Update , called every frame
     *
     * @param delta
     */
    abstract void update(float delta);

    abstract EntityState getState();

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public BaseRectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    abstract void takeHit(int hitAmount);

    /**
     *  Override this method in subclasses for specific behaviors
     */
    abstract void reset();

    abstract void updateLifes();

    public int getLifes(){
        return lifes;
    }

}
