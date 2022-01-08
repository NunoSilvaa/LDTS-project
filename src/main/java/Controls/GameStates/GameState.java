package Controls.GameStates;

import Controls.Game;
import menus.Gui;

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
