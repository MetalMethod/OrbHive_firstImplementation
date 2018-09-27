package games.metalmethod.orbhive.model.gameobjects;

import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.view.interfaces.BaseRectangle;
import games.metalmethod.orbhive.view.interfaces.Vector;

public class Enemy {

    protected Vector position;
    protected Vector velocity;
    protected Vector acceleration;

    protected int width;
    protected int height;

    private BaseRectangle boundingBox;

    protected boolean isScrolledLeft;

    public Enemy(float x, float y, int width, int height, float speed){
        position = new Vector(x, y);
        velocity = new Vector(speed, 0);

        this.width = width;
        this.height = height;

        isScrolledLeft = false;

        boundingBox = new BaseRectangle();
        boundingBox.set(position.x, position.y, width, height);

    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));
        boundingBox.setPosition(position.x, position.y);

        if(verifyIsScrolledLeft()){
            reset(Constants.enemyCreationX);
        }

    }

    // check if enemy went out of screen
    public boolean verifyIsScrolledLeft() {
        return position.x + width < 0;
    }

    // Override this method in subclasses for specific behaviors
    public void reset(float newX){
        position.x = newX;
        position.y = randomY();
        isScrolledLeft = false;
    }

    private float randomY() {
        return (float)Math.random() * (250f - 1f) + 1f;
    }

    public void stop(){
        velocity.x = 0;
        velocity.y = 0;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public Vector getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BaseRectangle getBoundingBox() {
        return boundingBox;
    }
}
