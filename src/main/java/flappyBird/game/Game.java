package flappyBird.game;


import flappyBird.controls.gameStates.GameState;
import flappyBird.controls.gameStates.InitialGameState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;
import flappyBird.menus.Gui;

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
        arena = new Arena(42, 42);
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

            arena.update(pressArrowUp);

            if(counter == 25){
                arena.addPipes();
                counter = 0;
            }

            if(arena.gameOver())
                gameOver = true;

            score++;
            counter++;

            draw();
            Thread.sleep(20);
        }
    }
}
