package games.metalmethod.orbhive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import games.metalmethod.orbhive.OrbHiveGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "OrbHive";
		config.width = 455 *2;
		config.height = 256 *2;

		new LwjglApplication(new OrbHiveGame(), config);
	}
}
