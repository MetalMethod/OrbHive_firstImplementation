package games.metalmethod.orbhive.model.gameobjects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.model.gameobjects.entities.BasicEnemy;
import games.metalmethod.orbhive.view.interfaces.GameMath;

public class EnemyFactory {

    // array containing the active bullets.
    private final Array<BasicEnemy> activeBasicEnemies = new Array<BasicEnemy>();

    // bullet pool.
    private final Pool<BasicEnemy> basicEnemyPool = new Pool<BasicEnemy>() {
        @Override
        protected BasicEnemy newObject() {
            return new BasicEnemy(Constants.enemyCreationX, GameMath.randomY(), 16, 16, -200);
        }
    };

    public BasicEnemy obtainBasicEnemy() {
        // if you want to spawn a new enemy:
        BasicEnemy item = basicEnemyPool.obtain();
        activeBasicEnemies.add(item);

        return item;
    }

    public void disposeEnemy(BasicEnemy basicEnemy) {
        // if you want to free dead enemies, returning them to the pool:
        int len = activeBasicEnemies.size;
        for (int i = len; --i >= 0; ) {
            basicEnemy = activeBasicEnemies.get(i);
            if (basicEnemy.getLifes() < 1) {
                activeBasicEnemies.removeIndex(i);
                basicEnemyPool.free(basicEnemy);
            }
        }
    }

    public void update(float delta) {
        int len = activeBasicEnemies.size;
        for (int i = len; --i >= 0; ) {
            BasicEnemy item = activeBasicEnemies.get(i);
            item.update(delta);

            handleEnemyOutOfScreen(item);
        }

    }

    private void handleEnemyOutOfScreen(BasicEnemy item) {
        if (item.verifyIsScrolledLeft() || item.verifyIsScrolledUp() || item.verifyIsScrolledDown()) {
            disposeEnemy(item);
        }
    }

}
