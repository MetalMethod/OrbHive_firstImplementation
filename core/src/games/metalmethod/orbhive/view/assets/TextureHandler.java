package games.metalmethod.orbhive.view.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.model.Constants;
import games.metalmethod.orbhive.model.gameobjects.Player;
import games.metalmethod.orbhive.model.gameworld.GameWorld;

public class TextureHandler {

    private Texture sprites;
    private GameWorld gameWorld;
    private OrthographicCamera camera;

    /**
     * Responsible for rendering shapes
     */
    private ShapeRenderer shapeRenderer;

    /**
     * Responsible for rendering images with indices x, y, width and height
     */
    private SpriteBatch batcher;

    /**
     * for calculating the window size
     */
    private int midPointY;
    private int gameHeight;

    /**
     * Game Objects
     */
    private Player player;
    private TextureRegion bg;
    private Animation playerAnimation;
    private TextureRegion playerFull, playerMid, playerLast;
    private Controller controller;

    public TextureHandler(Controller controller, GameWorld gameWorld, int gameHeight, int midPointY) {
        this.controller = controller;
        player = controller.getPlayer();

        this.sprites = AssetLoader.getSprites();

        this.gameWorld = gameWorld;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 455, 256);

        batcher = new SpriteBatch();
        //Attatch Batch to camera
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();

    }

    private void initGameObjects() {
        player = controller.getPlayer();
        //scrollHandler = gameWorld.getScrollHandler();
    }

    private void initAssets() {

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

        // TODO CLEAN COMMENTS
        // EXAMPLE OF ANIMATION
//        TextureRegion[] birds = { birdDown, bird, birdUp };
//        // Creates a new Animation in which each frame is 0.06 seconds long, using the above array.
//        birdAnimation = new Animation(0.06f, birds);
//        // Sets play mode to be ping pong, in which we will see a bounce.
//        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    /**
     * runTime is responsible to determine which frame the player animation should display.
     * The Animation object will use this value (and the frame duration) to determine which TextureRegion to display.
     *
     * @param runTime
     */
    public void render(float runTime) {

        drawBackgroundColor();

        batcher.begin();

        // Disable transparency - this is good for performance when drawing images that do not require transparency.
        batcher.disableBlending();

        drawBgTexture();

        // Draw elements that require transparency
        batcher.enableBlending();
        drawPlayer(runTime);

        batcher.end();

        // Draw non-bitmap elements
        drawPlayerBoundingRect();
    }

    private void fillBlackBg() {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawBgTexture() {
        int width = 32;
        int windowWidth = 455;
        int x = 0;
        while (windowWidth > x) {
            batcher.draw(bg, x, 0, width, 256);
            x += width;
        }
    }

    private void drawPlayer(float runTime) {
        TextureRegion playerState = playerFull;

        batcher.draw(
                playerState,
                ((int) player.getPosition().x),
                ((int) player.getPosition().y),
                50,
                50,
                Constants.playerSize,
                Constants.playerSize,
                1, 1,
                0
        );
    }


    private void drawShapes() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        // Draw Background color
//        shapeRenderer.setColor(128 / 255.0f, 128 / 255.0f, 128 / 255.0f, 1);
//        shapeRenderer.rect(0, 0, 455, 256);

        // Draw player bounding rectangle
        shapeRenderer.setColor(255f, 0f, 0f, 0f);
        shapeRenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y, player.getWidth(), player.getHeight());

        shapeRenderer.end();
    }

    private void drawBackgroundColor() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(128 / 255.0f, 128 / 255.0f, 128 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 455, 256);

        shapeRenderer.end();
    }

    private void drawPlayerBoundingRect() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw player bounding rectangle
        shapeRenderer.setColor(255f, 0f, 0f, 0f);
        shapeRenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y, player.getWidth(), player.getHeight());

        shapeRenderer.end();
    }
}

