package games.metalmethod.orbhive.gameobjects.entities;

import games.metalmethod.orbhive.Constants;
import games.metalmethod.orbhive.helpers.GameMath;
import games.metalmethod.orbhive.helpers.Vector;

public class Enemy extends GameEntity{

    private boolean isScrolledLeft;

    public Enemy(float x, float y, int width, int height, float speed){
        super(width, height, new Vector(x, y));
        position = new Vector(x, y);
        velocity = new Vector(speed, 0);
        acceleration = new Vector(0,0);

        this.width = width;
        this.height = height;

        isScrolledLeft = false;
        currentState = EntityState.FULL;
    }

@Override
    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        boundingRectangle.set(getPosition().x, getPosition().y , width, height);


        if(verifyIsScrolledLeft() || verifyIsScrolledUp() || verifyIsScrolledDown()){
            reset(Constants.enemyCreationX);
        }

    }

    @Override
    public EntityState getState() {
        return currentState;
    }

    // check if enemy went out of screen
    public boolean verifyIsScrolledLeft() {
        return position.x + width < 0;
    }
    public boolean verifyIsScrolledUp() {
        return position.y + height < 0;
    }
    public boolean verifyIsScrolledDown() {return position.y + height > Constants.screenHeight;
    }



    public void reset(float newX){
        position.x = newX;
        position.y = GameMath.randomY();
        isScrolledLeft = false;
        currentState = EntityState.FULL;
    }



//    public void stop(){
//        velocity.x = 0;
//        velocity.y = 0;
//    }

    public boolean isScrolledLeft() {
        isScrolledLeft = position.x + width < 0;
        return isScrolledLeft;
    }



    @Override
    public void takeHit(int hitAmount) {
        System.out.println("    enemyhit");
        currentState = EntityState.DEAD;
        reset(Constants.enemyCreationX);
    }

    @Override
    void reset() {

    }

    @Override
    void updateLifes() {

    }

}
