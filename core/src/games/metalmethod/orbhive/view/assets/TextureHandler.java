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
    private TextureRegion bg, halfDownBg;
    private Animation playerAnimation;
    private TextureRegion playerFull, playerMid, playerLast;
    private TextureRegion engineOne, engineTwo, engineThree;

    private TextureRegion playerExplosionOne, playerExplosionTwo, playerExplosionThree;
    private TextureRegion playerExplosionFour, playerExplosionFive, playerExplosionSix;


    private Animation engineAnimation;
    private Animation playerExplosionAnimation;


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
        halfDownBg = new TextureRegion(sprites, 224, 128, 32, 128);

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

        engineOne = new TextureRegion(sprites, 0, 199, 10, 10);
        engineOne.flip(false, true);

        engineTwo = new TextureRegion(sprites, 0, 208, 10, 10);
        engineTwo.flip(false, true);

        engineThree = new TextureRegion(sprites, 0, 217, 10, 10);
        engineThree.flip(false, true);

        TextureRegion[] engines = {engineOne, engineTwo, engineThree};
        engineAnimation = new Animation(0.06f, (Object[]) engines);
        engineAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        playerExplosionOne = new TextureRegion(sprites, 37, 193, 32, 32);
        playerExplosionTwo = new TextureRegion(sprites, 68, 193, 32, 32);
        playerExplosionThree = new TextureRegion(sprites, 98, 193, 32, 32);
        playerExplosionFour = new TextureRegion(sprites, 129, 193, 32, 32);
        playerExplosionFive = new TextureRegion(sprites, 161, 193, 32, 32);
        playerExplosionSix = new TextureRegion(sprites, 192, 193, 32, 32);
        playerExplosionOne.flip(false, true);
        playerExplosionTwo.flip(false, true);
        playerExplosionThree.flip(false, true);
        playerExplosionFour.flip(false, true);
        playerExplosionFive.flip(false, true);
        playerExplosionSix.flip(false, true);
        TextureRegion[] playerExplosions = {playerExplosionOne, playerExplosionTwo, playerExplosionThree, playerExplosionFour, playerExplosionFive, playerExplosionSix};
        playerExplosionAnimation = new Animation(0.15f, (Object[]) playerExplosions);
        playerExplosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);

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
        //drawHalfDownBgTexture();

        // Draw elements that require transparency
        batcher.enableBlending();
        drawPlayer(runTime);

        batcher.end();

        // Draw non-bitmap elements
//        drawPlayerBoundingRect();
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

    private void drawHalfDownBgTexture() {
        int width = 32;
        int windowWidth = 455;
        int x = 0;
        while (windowWidth > x) {
            batcher.draw(halfDownBg, x, 128, width, 128);
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

        if (controller.isPlayerMoving()) {
            drawEngine(runTime);
        }

        if (controller.isPlayerDying()) {
            drawPlayerExplosion(runTime);
        }

    }

    private void drawEngine(float runTime) {
        batcher.draw(
                (TextureRegion) engineAnimation.getKeyFrame(runTime),
                player.getPosition().x + 1,
                player.getPosition().y + 15,
                10,
                10
        );
    }

    private void drawPlayerExplosion(float runTime) {
        batcher.draw(
                (TextureRegion) playerExplosionAnimation.getKeyFrame(runTime),
                100,
                100,
                32,
                32
        );
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
        shapeRenderer.setColor(255f, 0f, 0f, 0.35f);
        shapeRenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y, player.getWidth(), player.getHeight());

        shapeRenderer.end();
    }


}

