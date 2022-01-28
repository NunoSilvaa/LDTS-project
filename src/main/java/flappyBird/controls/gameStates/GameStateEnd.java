package flappyBird.controls.gameStates;

import flappyBird.MusicPlayer;
import flappyBird.game.Game;
import flappyBird.menus.Gui;

public class GameStateEnd extends GameState{
    private MusicPlayer collideBird;
    public GameStateEnd(Game game) {
        super(game);
        collideBird = new MusicPlayer("gameover.wav");
    }
    public GameStateEnd() {
        super();
        collideBird = new MusicPlayer("gameover.wav");
    }

    public void setSound(MusicPlayer sound){this.collideBird = sound;}

    @Override
    public boolean execute(Gui gui) throws Throwable {
        collideBird.playSound();
        gui.endMenu(game.getScore());
        Thread.sleep(5000);
        return true;
    }
}
