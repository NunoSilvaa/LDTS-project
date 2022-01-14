package flappyBird.game;

import flappyBird.menus.Gui;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game(new Gui());
        try {
            game.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
