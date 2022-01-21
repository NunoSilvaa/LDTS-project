package flappyBird.controls.gameStates;

import flappyBird.game.Game;
import com.googlecode.lanterna.input.KeyType;
import flappyBird.menus.Gui;

import java.io.IOException;

public class InitialGameState extends GameState {

    public InitialGameState(Game game){
        super(game);
    }
    public InitialGameState(){
        super();
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        gui.mainMenu(3);
        KeyType keyType = gui.getKeyStroke().getKeyType();
        if (keyType != KeyType.Escape && keyType != KeyType.EOF){
            switch (processOption(gui)){
                case 3:
                    //System.out.println("Hello");
                    game.changeGameState(new GameStateRun(game));
                    //gui.pauseMenu(0);
                    return false;
                case 1:
                    return true;
            }
        }
        else{
            return true;
        }
        return false;
    }

    public int processOption(Gui gui) throws IOException {
        int option = 3;
        gui.mainMenu(option);
        KeyType keyType;
        do {
            keyType = gui.getKeyStroke().getKeyType();
            if (keyType == KeyType.ArrowDown) {
                option = Math.abs(option - 1);
                option = rangeLimiter(option);
                System.out.println(option);
                gui.mainMenu(option);
            }
            if (keyType == KeyType.ArrowUp) {
                option = Math.abs(option + 1);
                option = rangeLimiter(option);
                System.out.println(option);
                gui.mainMenu(option);
            }
            if (keyType == KeyType.Escape || keyType == KeyType.EOF) {
                //game.changeGameState(new GameStateReady(game));
            }
        } while (keyType != KeyType.Enter);

        System.out.println(option);

        return option;
    }

    public int rangeLimiter(int num){
        if(num > 3)
            num = 3;
        if(num < 1)
            num = 1;
        return num;
    }
}

