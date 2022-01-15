package flappyBird.game;

import flappyBird.menus.Gui;
import java.io.IOException;

public class Application {
    private static Game singleton = null;

    public static Game getInstance() throws IOException{
        if(singleton == null)
            singleton = new Game(new Gui());
        return singleton;
    }


    public static void main(String[] args) throws Throwable {
        Game game = getInstance();
        game.start();
    }
}
