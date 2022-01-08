package Controls.GameStates;

import Controls.Game;
import menus.Gui;

public class GameStateEnd extends GameState{
    public GameStateEnd(Game game) {
        super(game);
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        return false;

    }
}
