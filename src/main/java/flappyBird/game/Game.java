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

    /*public void run() throws IOException, InterruptedException {
        score = 0;
        counter = 0;
        boolean pressArrowUp;

        while(!gameOver) {
            KeyStroke key = gui.getTerminal().pollInput();
            if (key != null && key.getKeyType() == KeyType.ArrowUp)
                pressArrowUp = true;
            else if (key != null && key.getKeyType() == KeyType.Escape) {
                pressArrowUp = false;
                pressEscape = true;
            }
            else
                pressArrowUp = false;

            if(!arena.update(pressArrowUp) /*|| arena.verifyPipeCollisions()){
                gameOver = true;
                continue;
            }


            if(counter == 25){
                arena.createPipes();
                counter = 0;
            }
            //score++;
            counter++;

            //System.out.println(arena.getPipes().size());

            draw();
            addScore(arena);
            Thread.sleep(100);
        }
    }*/

    public void addScore(Arena arena){
        //ListIterator<Pipe> pipe = pipes.listIterator();
        //System.out.println(arena.getPipes().size());
        System.out.println("Bird");
        System.out.println(arena.getBird().getPosition().getX());
        for(int i = 0; i < arena.getPipes().size(); i++)
            if(arena.getBird().getPosition().getX() > arena.getPipes().get(i).getPosition().getX()){
                System.out.println("Pipe");
                System.out.println(arena.getPipes().get(i).getPosition().getX());
                score++;
                i++;
                i++;
                break;
                //System.out.println(i);
            }
    }
}
