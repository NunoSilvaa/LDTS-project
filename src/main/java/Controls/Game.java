package Controls;


import Controls.GameStates.GameState;
import Controls.GameStates.InitialGameState;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;
import menus.Gui;

public class Game {
    private Arena arena;
    private Gui gui;
    private GameState gameState;
    private int score;
    private int counter;
    public boolean gameOver;


    public Game(Gui gui) throws IOException {
        this.gui = gui;
        this.gameState = new InitialGameState(this);
        arena = new Arena(40, 20);
        gameOver = false;
    }

    public int getScore(){return score;}

    public void changeGameState(GameState gameState) { this.gameState = gameState; }

    public void start() throws Throwable {
        boolean close;
        do {
            close = gameState.execute(gui);
        } while (!close);
        gui.close();
    }

    public void draw() throws IOException{
        gui.getScreen().clear();
        arena.draw(gui.getScreen().newTextGraphics());
        gui.getScreen().refresh();
    }

    public void run() throws IOException, InterruptedException {
        counter = 0;
        score = 0;
        boolean pressArrowUp;

        while(!gameOver) {
            KeyStroke key = gui.getTerminal().pollInput();
            if (key != null && key.getKeyType() == KeyType.ArrowUp)
                pressArrowUp = true;
            else
                pressArrowUp = false;

            if(!arena.update(pressArrowUp) || arena.verifyPipeCollisions()){
                gameOver = true;
                continue;
            }

            if(counter == 25){
                arena.createPipes();
                counter = 0;
            }
            score++;
            counter++;

            draw();
            Thread.sleep(20);
        }
    }
}
