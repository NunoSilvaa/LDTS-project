package Controls.GameStates;

import Controls.Game;
import menus.Gui;

public abstract class GameState {
    Game game;

    public GameState(Game game){
        this.game = game;
    }

    public abstract boolean execute(Gui gui) throws Throwable;
}
