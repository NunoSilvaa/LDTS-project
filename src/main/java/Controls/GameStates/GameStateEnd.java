package Controls.GameStates;

import Controls.Game;
import menus.Gui;

public class GameStateEnd extends GameState{
    public GameStateEnd(Game game) {
        super(game);
    }
    public GameStateEnd() {super();}

    @Override
    public boolean execute(Gui gui) throws Throwable {
        gui.endMenu(game.getScore());
        Thread.sleep(5000);
        return true;
    }
}
