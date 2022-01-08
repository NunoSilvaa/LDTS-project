package Controls.GameStates;

import Controls.Game;
import com.googlecode.lanterna.input.KeyType;
import menus.Gui;

import java.io.IOException;

public class InitialGameState extends GameState {

    public InitialGameState(Game game){
        super(game);
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        return false;
    }

    public int processOption(Gui gui) throws IOException {
        return 0;

    }

    public int rangeLimiter(int num){
        return 0;

    }
}

