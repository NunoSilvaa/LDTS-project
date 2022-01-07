import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;

public class Game {
    private Arena arena;
    private Terminal terminal;
    private Screen screen;
    public boolean gameOver;


    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(42, 42);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        arena = new Arena(42, 42);
        gameOver = false;
    }


    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException, InterruptedException {
        int counter =0;
        boolean pressArrowUp;
        draw();

        while(!gameOver) {
            KeyStroke key = terminal.pollInput();
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
        }
        Thread.sleep(3000);
        screen.close();
    }
}
