package games.metalmethod.orbhive.view.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import games.metalmethod.orbhive.controller.Controller;
import games.metalmethod.orbhive.model.gameobjects.Player;
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
            case 51:
            case 19: {
                this.movePlayerUp();
                break;
            }
            case 32:
            case 22: {
                this.movePlayerForward();
                break;
            }
            case 47:
            case 20: {
                this.movePlayerDown();
                break;
            }
            case 29:
            case 21: {
                this.movePlayerBack();
                break;
            }

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 51:
            case 19: {
                this.stopMovePlayerUp();
                break;
            }
            case 32:
            case 22: {
                this.stopMovePlayerForward();
                break;
            }
            case 47:
            case 20: {
                this.stopMovePlayerDown();
                break;
            }
            case 29:
            case 21: {
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

}
