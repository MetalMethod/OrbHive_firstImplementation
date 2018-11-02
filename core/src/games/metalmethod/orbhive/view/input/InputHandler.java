package games.metalmethod.orbhive.view.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.model.gameobjects.entities.Player;
import games.metalmethod.orbhive.model.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

    private Controller controller;
    private GameWorld gameWorld;
    private Player player;

    public InputHandler(Controller controller) {

        this.controller = controller;
        this.gameWorld = controller.getGameWorld();
        this.player = controller.getPlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
//        Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                this.movePlayerUp();
                break;
            }
            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                this.movePlayerForward();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {
                this.movePlayerDown();
                break;
            }
            case Input.Keys.A:
            case Input.Keys.LEFT: {
                this.movePlayerBack();
                break;
            }

            case Input.Keys.SPACE:
            case Input.Keys.M: {
                this.playerShoot();
            }
        }
        return true;
    }



    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                this.stopMovePlayerUp();
                break;
            }
            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                this.stopMovePlayerForward();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {
                this.stopMovePlayerDown();
                break;
            }
            case Input.Keys.A:
            case Input.Keys.LEFT: {
                this.stopMovePlayerBack();
                break;
            }
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void movePlayerUp() {
        this.controller.movePlayerUp();
    }

    private void movePlayerForward() {
        this.controller.movePlayerForward();
    }

    private void movePlayerBack() {
        this.controller.movePlayerBack();
    }

    private void movePlayerDown() {
        this.controller.movePlayerDown();
    }

    private void stopMovePlayerUp() {
        this.controller.stopMovePlayerY();
    }

    private void stopMovePlayerForward() {
        this.controller.stopMovePlayerX();
    }

    private void stopMovePlayerDown() {
        this.controller.stopMovePlayerY();
    }

    private void stopMovePlayerBack() {
        this.controller.stopMovePlayerX();
    }

    private void stopPlayer() {
        this.controller.stopPlayer();
    }

    private void playerShoot() {
        controller.playerShoot();
    }
}
