package games.metalmethod.orbhive.gameobjects;

import games.metalmethod.orbhive.gameobjects.entities.Bullet;
import games.metalmethod.orbhive.gameobjects.entities.Player;

public class BulletFactory {

    public Bullet getBullet(Player player){
        return new Bullet(player);
    }
}
