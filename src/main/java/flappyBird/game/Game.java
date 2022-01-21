package flappyBird.game;


import flappyBird.controls.gameStates.GameState;
import flappyBird.controls.gameStates.InitialGameState;
import java.io.IOException;
import flappyBird.menus.Gui;

public class Game {
    private Arena arena;
    private Gui gui;
    private GameState gameState;
    private int score;
    public boolean gameOver;
    private boolean running;
    private boolean pressEscape;


    public Game(Gui gui) throws IOException {
        this.gui = gui;
        this.gameState = new InitialGameState(this);
        arena = new Arena(60, 30);
        gameOver = false;
    }

    public boolean isRunning(){return running;}
    public boolean pressedEscape(){return pressEscape;}

    public int getScore(){return score;}
    public Arena getArena(){return arena;}
    public GameState getState(){return gameState;}

    public void setRunning(boolean running) { this.running = running; }
    public void setPressEscape(boolean pressEscape) { this.pressEscape = pressEscape; }
    public void setScore(int score) { this.score = score; }

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

}
