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

        //frontGrass = scrollHandler.getFrontGrass();
        //backGrass = scrollHandler.getBackGrass();

    }

    private void initAssets() {
        bg = AssetLoader.bg;
        playerFull = AssetLoader.playerFull;
    }
//
//    private void drawGrass() {
//        // Draw the grass
//        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
//                frontGrass.getWidth(), frontGrass.getHeight());
//        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
//                backGrass.getWidth(), backGrass.getHeight());
// }

    /**
     * runTime is reponsible to determine which frame the bird animation should display.
     * The Animation object will use this value (and the frame duration) to determine which TextureRegion to display.
     *
     * @param runTime
     */
    public void render(float runTime) {

        // Fill the entire screen with black, to prevent potential flickering.
        fillBlackBg();

        // Draw non-bitmap elements
//        drawShapes();

        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency - this is good for performance when drawing images that do not require transparency.
        batcher.disableBlending();

        drawBgTexture();

        // Draw elements that require transparency
        batcher.enableBlending();
  //      drawPlayer(runTime);

        // End SpriteBatch
        batcher.end();
    }

    private void fillBlackBg() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawShapes() {
        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(128 / 255.0f, 128 / 255.0f, 128 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(45 / 255.0f, 45 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(27 / 255.0f, 27 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // End ShapeRenderer
        shapeRenderer.end();
    }

    private void drawBgTexture() {
        // batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);
        int w = 32;
        int windowWidth = 455;
        int d = 0;
        int x = 0;
        while(windowWidth > x){
            batcher.draw(AssetLoader.bg, x, 0, 32, 256);
            x += w;
        }
    }

//    private void drawPlayer(float runTime) {
//        batcher.draw(
//                birdMid,
//                player.getX(),
//                player.getY(),
//                player.getWidth() / 2.0f,
//                player.getHeight() / 2.0f,
//                player.getWidth(),
//                player.getHeight(),
//                1, 1,
//                player.getRotation());
//    }

    private void drawPlayer(float runTime) {
        batcher.draw(
                playerFull,
                50,
                50,
                50,
                50,
                40,
                40,
                1, 1,
                0);
    }

}

