package Controls.GameStates;

import Controls.Game;
import menus.Gui;

public class GameStateRun extends GameState {
    public GameStateRun(Game game) {
        super(game);
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        return false;
    }
}
