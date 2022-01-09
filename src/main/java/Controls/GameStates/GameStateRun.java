package Controls.GameStates;

import Controls.Game;
import menus.Gui;

public class GameStateRun extends GameState {
    public GameStateRun(Game game) {
        super(game);
    }
    public GameStateRun() {
        super();
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        game.draw();
        Thread.sleep(5000);
        game.run();
        Thread.sleep(1000);
        game.changeGameState(new GameStateEnd(game));
        return false;
    }
}
