package games.metalmethod.orbhive.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture sprites;
    public static TextureRegion bg;
    public static TextureRegion playerFull, playerMid, playerLast;

    public static void load() {
        sprites = new Texture(Gdx.files.internal("sprites.png"));

        // filters for pixel art, nearest neighbor scaling up
        sprites.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(sprites, 224, 0, 32, 256);

        playerFull = new TextureRegion(
                sprites,
                0,
                0,
                39,
                40
        );
        playerMid = new TextureRegion(
                sprites,
                40,
                0,
                39,
                40
        );
        playerLast = new TextureRegion(
                sprites,
                79,
                0,
                39,
                40
        );
        // image must be flipped because default coordinate system is Y Up and this game uses U Down
        playerFull.flip(false, true);
        playerMid.flip(false, true);
        playerLast.flip(false, true);

//        grass = new TextureRegion(sprites, 0, 43, 143, 11);
//        grass.flip(false, true);
//
//        birdDown = new TextureRegion(sprites, 136, 0, 17, 12);
//        birdDown.flip(false, true);
//
//        bird = new TextureRegion(sprites, 153, 0, 17, 12);
//        bird.flip(false, true);
//
//        birdUp = new TextureRegion(sprites, 170, 0, 17, 12);
//        birdUp.flip(false, true);


//        TextureRegion[] birds = { birdDown, bird, birdUp };
//        // Creates a new Animation in which each frame is 0.06 seconds long, using the above array.
//        birdAnimation = new Animation(0.06f, birds);
//        // Sets play mode to be ping pong, in which we will see a bounce.
//        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

        // We must dispose of the sprites whens we are finished.
    public static void dispose() {
        sprites.dispose();
    }
}