package games.metalmethod.orbhive.model.gameobjects.entities;

import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.view.interfaces.Vector;

public class Player extends GameEntity {

    public Player(int width, int height, Vector position) {
        super(width, height, position);

        this.acceleration = new Vector(Constants.wind, Constants.gravity);

        currentState = EntityState.FULL;
        lifes = Constants.initialEntityLives;
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));


        boundingRectangle.set(getPosition().x, getPosition().y , width, height);

        updateLifes();

    }

    protected void updateLifes() {
        if (this.lifes < 1) {
            this.lifes = Constants.initialEntityLives;
        }
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

    public void takeHit(int hitAmount) {
        this.velocity.set(this.getVelocity().add(-hitAmount, 0));

        this.lifes--;

        System.out.println("hit. remaining lifes: " + String.valueOf(this.lifes));

    }

    @Override
    void reset() {

    }

    public EntityState getState() {
        switch (this.lifes) {
            case 3:
                currentState = EntityState.FULL;
                break;
            case 2:
                currentState = EntityState.MID;
                break;
            case 1:
                currentState = EntityState.LAST;
                break;
            case 0:
                currentState = EntityState.DEAD;
                break;
        }
        return currentState;
    }

    public void shoot(){
        System.out.println("shoot");
    }
}
