package games.metalmethod.orbhive.model.gameobjects;

import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.view.interfaces.BaseRectangle;
import games.metalmethod.orbhive.view.interfaces.Vector;

public class Player {

    private Vector position;
    private Vector velocity;
    private Vector acceleration;

    /**
     * positive change in rotation is a clockwise rotation and that a negative change in rotation is a counterclockwise rotation.
     */
    private float rotation;

    private int width;
    private int height;


    /**
     * Object for collision detection
     */
    private BaseRectangle boundingRectangle;

    private PlayerState currentState;
    private int lifes;

    public Player(float x, float y) {
        this.width = 40;
        this.height = 25;

        this.position = new Vector(x, y);
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(Constants.wind, Constants.gravity);

        boundingRectangle = new BaseRectangle();

        this.currentState = PlayerState.FULL;
        this.lifes = Constants.initialLives;
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        boundingRectangle.set(getPosition().x, getPosition().y + 7, width, height);

        updateLifes();
    }

    private void updateLifes() {
        if (this.lifes < 1) {
            this.lifes = Constants.initialLives;
        }
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BaseRectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void takeHit(int hitAmount) {
        this.velocity.set(this.getVelocity().add(-hitAmount, 0));

        this.lifes--;

        System.out.println("lifes");
        System.out.println(this.lifes);
    }

    public PlayerState getState() {
        switch (this.lifes) {
            case 3:
                currentState = PlayerState.FULL;
                break;
            case 2:
                currentState = PlayerState.MID;
                break;
            case 1:
                currentState = PlayerState.LAST;
                break;
            case 0:
                currentState = PlayerState.DEAD;
                break;
        }
        return currentState;
    }
}
