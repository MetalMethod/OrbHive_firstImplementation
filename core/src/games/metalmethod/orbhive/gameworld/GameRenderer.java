package games.metalmethod.orbhive.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import games.metalmethod.orbhive.gameobjects.Player;
import games.metalmethod.orbhive.helpers.AssetLoader;

public class GameRenderer {

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

    public GameRenderer(GameWorld gameWorld, int gameHeight, int midPointY) {
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
//        player = gameWorld.getPlayer();
        //scrollHandler = gameWorld.getScrollHandler();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        playerFull = AssetLoader.playerFull;
        playerMid = AssetLoader.playerMid;
        playerLast = AssetLoader.playerLast;
    }

    /**
     * runTime is reponsible to determine which frame the bird animation should display.
     * The Animation object will use this value (and the frame duration) to determine which TextureRegion to display.
     *
     * @param runTime
     */
    public void render(float runTime) {

        // Draw non-bitmap elements
        drawShapes();

        batcher.begin();

        // Disable transparency - this is good for performance when drawing images that do not require transparency.
        batcher.disableBlending();

        drawBgTexture();

        // Draw elements that require transparency
        batcher.enableBlending();
        drawPlayer(runTime);

        batcher.end();
    }

    private void fillBlackBg() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawShapes() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(128 / 255.0f, 128 / 255.0f, 128 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 455, 256);

        shapeRenderer.end();
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
                50,
                50,
                50,
                50,
                40,
                40,
                1, 1,
                0
        );
    }

}

