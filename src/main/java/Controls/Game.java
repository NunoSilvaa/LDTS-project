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
    private Terminal terminal;
    private Screen screen;
    private Gui gui;
    private GameState gameState;
    private int score;
    public boolean gameOver;


    public Game(Gui gui) throws IOException {
        this.gui = gui;
        this.gameState = new InitialGameState(this);
        /*TerminalSize terminalSize = new TerminalSize(42, 42);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();*/
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
        score = 0;
        boolean pressArrowUp;
        //draw();

        //Thread.sleep(5000);

        /*KeyStroke key = gui.getTerminal().pollInput();
        if (key != null && key.getKeyType() == KeyType.ArrowUp)
            pressArrowUp = true;
        else
            pressArrowUp = false;

        if(!arena.update(pressArrowUp) || arena.verifyPipeCollisions()){
            gameOver = true;
            //continue;
        }

        if(score == 25){
            arena.createPipes();
            score = 0;
        }
        score++;

        draw();
        Thread.sleep(50);*/

        /*while(!gameOver) {
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
            counter++;

            draw();
            Thread.sleep(20);
        }*/
        //Thread.sleep(1000);
        //gui.getScreen().close();
    }
}
