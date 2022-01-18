package flappyBird.controls.gameStates;

import flappyBird.game.Game;
import flappyBird.menus.Gui;

public abstract class GameState {
    Game game;

    public GameState(Game game){
        this.game = game;
    }

    public GameState(){}

    public abstract boolean execute(Gui gui) throws Throwable;

    void setGame(Game game){
        this.game = game;
    }
}
