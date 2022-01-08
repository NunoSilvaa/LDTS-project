package menus;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Gui {
    private Screen screen;
    private Terminal terminal;

    public Gui(){
        try {
            URL resource = getClass().getClassLoader().getResource("monogram.ttf");//new URL("https://cdn.discordapp.com/attachments/909096573319929867/925078788059037766/flappy-font.ttf"); //getClass().getClassLoader().getResource("flappy-font.ttf");
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, resource.openStream());//new Font("Monospaced", Font.PLAIN, 10);//new Font("Monospaced", Font.PLAIN, 10); //Font.createFont(Font.TRUETYPE_FONT, resource.openStream());//new Font("Monospaced", Font.PLAIN, 10);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();

            Font loadedFont = font.deriveFont(Font.PLAIN, 40);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(40, 20));

            terminal = factory.createTerminal();
            ((AWTTerminalFrame) terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });

            setScreen(new TerminalScreen(terminal));

            startScreen();
        }catch (IOException | FontFormatException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    public Gui(Screen screen) {
        setScreen(screen);
    }

    public Screen getScreen() {
        return screen;
    }

    public Terminal getTerminal() {return terminal;}

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void startScreen() throws IOException {
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void close() throws Throwable {
        if (screen != null) {
            screen.close();
        }
    }

    public KeyStroke getKeyStroke() throws IOException {
        return screen.readInput();
    }

    public Moves getMove() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case ArrowUp:
                    return Moves.UP;
                case ArrowDown:
                    return Moves.DOWN;
                case Escape:
                case EOF:
                    return Moves.ESC;
                default:
                    return null;
            }
        }
        return null;
    }


    public void mainMenu(int i) throws IOException{
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));
        textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"));
        textGraphics.fillRectangle(new TerminalPosition(0, 15), screen.getTerminalSize(), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"));
        textGraphics.fillRectangle(new TerminalPosition(0, 15), new TerminalSize(40, 1), ' ');

        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));
        //loadedFont.deriveFont(30);
        textGraphics.putString(15, 3, "Flappy Bird", SGR.BOLD);

        if(i == 3){
            textGraphics.putString(18, 7, "Play", SGR.BLINK);
            textGraphics.putString(16, 10, "Scoreboard");
            textGraphics.putString(18, 13, "Quit");
        }
        else if(i == 2){
            textGraphics.putString(18, 7, "Play");
            textGraphics.putString(16, 10, "Scoreboard", SGR.BLINK);
            textGraphics.putString(18, 13, "Quit");
        }
        else if(i == 1){
            textGraphics.putString(18, 7, "Play");
            textGraphics.putString(16, 10, "Scoreboard");
            textGraphics.putString(18, 13, "Quit", SGR.BLINK);
        }

        screen.refresh();

    }

    public void pauseMenu(int i) throws IOException{
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));
        textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"));
        textGraphics.fillRectangle(new TerminalPosition(0, 15), screen.getTerminalSize(), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"));
        textGraphics.fillRectangle(new TerminalPosition(0, 15), new TerminalSize(40, 1), ' ');

        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));

        if(i == 0){
            textGraphics.putString(17, 7, "Resume", SGR.BLINK);
            textGraphics.putString(18, 10, "Quit");
        }
        else if(i == -1){
            textGraphics.putString(17, 7, "Resume");
            textGraphics.putString(18, 10, "Quit", SGR.BLINK);
        }

        screen.refresh();
    }

    public void endMenu(int score) throws IOException{
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));
        textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"));
        textGraphics.fillRectangle(new TerminalPosition(0, 15), screen.getTerminalSize(), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"));
        textGraphics.fillRectangle(new TerminalPosition(0, 15), new TerminalSize(40, 1), ' ');

        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));

        textGraphics.putString(16, 7, "Game Over", SGR.BOLD);
        textGraphics.putString(17, 10, "Score:");
        textGraphics.putString(23, 10, String.valueOf(score));

        screen.refresh();
    }
}


