package games.metalmethod.orbhive.gameobjects.entities;

import games.metalmethod.orbhive.helpers.Vector;

public class Bullet extends GameEntity {


    public Bullet(Player player) {

        super(10, 5, new Vector(player.getPosition().x, player.getPosition().y));

    }

    @Override
    void update(float delta) {

    }

    @Override
    EntityState getState() {
        return null;
    }

    @Override
    void takeHit(int hitAmount) {

    }

    @Override
    void reset() {

    }

    @Override
    void updateLifes() {

    }
}
