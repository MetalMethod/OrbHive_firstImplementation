package games.metalmethod.orbhive.model.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    /**
     * positive change in rotation is a clockwise rotation and that a negative change in rotation is a counterclockwise rotation.
     */
    private float rotation;

    private int width;
    private int height;

    /**
     * Object for collision detection
     */
    private Circle boundingCircle;

    public Player( float x, float y, int width, int height) {
        this.width = width;
        this.height = height;

        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0,0);
        this.acceleration =  new Vector2(0,0);

        boundingCircle = new Circle();

    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        // Set the circle's center to be (9, 6) with respect to the bird.
        // Set the circle's radius to be 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
