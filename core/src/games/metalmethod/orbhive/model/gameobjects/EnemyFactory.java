package games.metalmethod.orbhive.model.gameobjects;


import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.model.gameobjects.entities.Enemy;

public class EnemyFactory {


    public Enemy createEnemy(){
        return new Enemy(Constants.enemyCreationX, 150, 16 , 16, -200);
    }

    public void disposeEnemy(Enemy enemy){
        enemy = null;
    }


}
