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
    public EntityState getState() {
        return EntityState.FULL;
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
