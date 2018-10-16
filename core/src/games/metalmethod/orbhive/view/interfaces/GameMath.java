package games.metalmethod.orbhive.view.interfaces;

import com.badlogic.gdx.math.MathUtils;

public class GameMath {

    public static float randomY() {
        return MathUtils.random(1, 250);
//        return (float)GameMath.random() * (250f - 1f) + 1f;
    }
}

