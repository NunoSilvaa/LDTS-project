package flappyBird.controls.gameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import flappyBird.game.Game;
import flappyBird.game.states.NormalState;
import flappyBird.menus.Gui;

import java.io.IOException;

public class GameStateRun extends GameState {
    public GameStateRun(Game game) {
        super(game);
    }
    public GameStateRun() {
        super();
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        int counter = 0;
        int powerCounter =0;
        int score = 0;
        int enemyCounter = 0;
        int powerLast =0;
        boolean pressArrowUp;
        boolean pressEscape = false;
        game.setRunning(true);
        while(game.isRunning()) {
            KeyStroke key = gui.getTerminal().pollInput();
            if (key != null && key.getKeyType() == KeyType.ArrowUp)
                pressArrowUp = true;
            else if (key != null && key.getKeyType() == KeyType.Escape) {
                pressArrowUp = false;
                pressEscape = true;
            } else
                pressArrowUp = false;

            if (pressEscape) {
                if (pauseScreen(gui)) {
                    return true;
                }
                pressArrowUp = true;
                pressEscape = false;
            }
            game.getArena().collideEntities();
            if (!game.getArena().update(pressArrowUp)) {
                game.setRunning(false);
                continue;
            }
            if(powerLast == 40 ){game.getArena().setState(new NormalState(game.getArena()));}
            if (powerCounter == 55){
                game.getArena().addPowerUp();
                powerLast =0;
                powerCounter = 0;
            }

            if (enemyCounter == 10){
                game.getArena().addEnemies();
                enemyCounter = 0;
            }

            if (counter == 25) {
                game.getArena().addPipes();
                counter = 0;
            }
            score++;
            counter++;
            powerCounter++;
            enemyCounter++;
            powerLast++;

            game.draw();
            Thread.sleep(100);
        }
            Thread.sleep(1000);
            game.setScore(score);
            game.changeGameState(new GameStateEnd(game));

            return false;
    }

    public boolean pauseScreen(Gui gui) throws IOException {
        int option = 3;
        gui.pauseMenu(option);
        KeyType keyType;
        do{
            keyType = gui.getKeyStroke().getKeyType();
            if (keyType == KeyType.ArrowDown) {
                option = Math.abs(option - 1);
                option = rangeLimiter(option);
                gui.pauseMenu(option);
            }
            if (keyType == KeyType.ArrowUp) {
                option = Math.abs(option + 1);
                option = rangeLimiter(option);
                gui.pauseMenu(option);
            }
        }while (keyType != KeyType.Enter);

        return option == 2;
    }

    public int rangeLimiter(int num){
        if(num > 3)
            num = 3;
        if(num < 2)
            num = 2;
        return num;
    }
}
