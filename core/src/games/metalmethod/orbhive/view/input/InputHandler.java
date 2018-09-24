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
        //Gdx.app.log("key pressed: ", String.valueOf(keycode));
        switch (keycode) {
            case 51: {
                this.movePlayerUp();
                break;
            }
            case 32: {
                this.movePlayerForward();
                break;
            }
            case 47: {
                this.movePlayerDown();
                break;
            }
            case 29: {
                this.movePlayerBack();
                break;
            }

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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
        Gdx.app.log("mode player: ", "UP");
        this.controller.movePlayerUp();
    }

    private void movePlayerForward() {
        Gdx.app.log("mode player: ", "FORWARD");
    }

    private void movePlayerBack() {
        Gdx.app.log("mode player: ", "BACK");
    }

    private void movePlayerDown() {
        Gdx.app.log("mode player: ", "DOWN");
    }
}
