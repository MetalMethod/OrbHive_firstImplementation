package games.metalmethod.orbhive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import games.metalmethod.orbhive.Controller;
import games.metalmethod.orbhive.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "OrbHive";
		config.width = Constants.screenWidth *2;
		config.height = Constants.screenHeight *2;

		new LwjglApplication(new Controller(), config);
	}
}
