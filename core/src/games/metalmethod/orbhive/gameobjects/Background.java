package games.metalmethod.orbhive.gameobjects;

import games.metalmethod.orbhive.helpers.Vector;

public class Background {

    private Vector position;
    private Vector velocity;
    private int width;
    private int height;
    private boolean isScrolledLeft;

    public Background(float x, float y, int width, int height, float scrollSpeed){
        position = new Vector(x, y);
        velocity = new Vector(scrollSpeed, 0);
        this.width = width;
        this.height = height;

        isScrolledLeft = false;
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));

        if(position.x + width < 0){
            isScrolledLeft = true;
        }
    }

    public void reset(float newX){
        position.x = newX;
        isScrolledLeft = false;
    }

    public void stop(){
        velocity.x = 0;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

}
