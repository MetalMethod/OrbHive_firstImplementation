package games.metalmethod.orbhive.gameobjects;


import games.metalmethod.orbhive.gameobjects.entities.Enemy;
import games.metalmethod.orbhive.Constants;

public class EnemyFactory {


    public Enemy createEnemy(){
        return new Enemy(Constants.enemyCreationX, 150, 16 , 16, -200);
    }

    public void disposeEnemy(Enemy enemy){
        enemy = null;
    }


}
