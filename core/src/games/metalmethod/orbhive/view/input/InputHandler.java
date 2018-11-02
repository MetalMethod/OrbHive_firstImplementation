package games.metalmethod.orbhive.view.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import games.metalmethod.orbhive.controller.Controller;

public class InputHandler implements InputProcessor {

    private Controller controller;

    public InputHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public boolean keyDown(int keycode) {
//        Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                controller.movePlayerUp();
                break;
            }
            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                controller.movePlayerForward();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {
                controller.movePlayerDown();
                break;
            }
            case Input.Keys.A:
            case Input.Keys.LEFT: {
                controller.movePlayerBack();
                break;
            }

            case Input.Keys.SPACE:
            case Input.Keys.M: {
                controller.playerShoot();
            }
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP: {
                controller.stopMovePlayerY();
                break;
            }
            case Input.Keys.D:
            case Input.Keys.RIGHT: {
                controller.stopMovePlayerX();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN: {
                controller.stopMovePlayerY();
                break;
            }
            case Input.Keys.A:
            case Input.Keys.LEFT: {
                controller.stopMovePlayerX();
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

}
