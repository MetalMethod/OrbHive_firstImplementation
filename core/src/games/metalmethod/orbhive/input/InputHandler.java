package games.metalmethod.orbhive.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import games.metalmethod.orbhive.Controller;

public class InputHandler implements InputProcessor {

    private Controller controller;

    public InputHandler(Controller controller) {

        this.controller = controller;
    }

    @Override
    public boolean keyDown(int keycode) {
        // Gdx.app.log("key pressed: ", String.valueOf(keycode));

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
                break;
            }

        }
        return true;
    }

    private void playerShoot() {
        controller.playerShoot();
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                stopMovePlayerUp();
                break;
            }
            case Input.Keys.F:
            case Input.Keys.RIGHT: {
                stopMovePlayerForward();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {
                stopMovePlayerDown();
                break;
            }
            case Input.Keys.A:
            case Input.Keys.BACK: {
                stopMovePlayerBack();
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
        controller.movePlayerUp();
    }

    private void movePlayerForward() {
        controller.movePlayerForward();
    }

    private void movePlayerBack() {
        controller.movePlayerBack();
    }

    private void movePlayerDown() {
        controller.movePlayerDown();
    }

    private void stopMovePlayerUp() {
        controller.stopMovePlayerY();
    }

    private void stopMovePlayerForward() {
        controller.stopMovePlayerX();
    }

    private void stopMovePlayerDown() {
        controller.stopMovePlayerY();
    }

    private void stopMovePlayerBack() {
        controller.stopMovePlayerX();
    }

    private void stopPlayer() {
        controller.stopPlayer();
    }

}
