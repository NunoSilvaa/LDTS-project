package menus

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification
import static com.googlecode.lanterna.input.KeyType.*;

class GuiTest extends Specification{

    def 'close'() {
        given:
        Screen screen = Mock(Screen.class)
        Gui gui = new Gui(screen)

        when:
        gui.close()

        then:
        verifyAll {
            screen.close()
        }
    }


    def 'getMove'() {
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        screen.pollInput()
        gui.getMove() == null

        screen.pollInput() >> new KeyStroke(ArrowUp)
        screen.pollInput() >> new KeyStroke(ArrowDown)
        screen.pollInput() >> new KeyStroke(Escape)

        when:
        Moves.UP == gui.getMove()

        then:
        1 * screen.pollInput()

        when:
        Moves.DOWN == gui.getMove()

        then:
        1 * screen.pollInput()


        when:
        Moves.ESC == gui.getMove()

        then:
        1 * screen.pollInput()
    }

    def 'mainMenu'(){
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        def textGraphics = Mock(TextGraphics.class)

        screen.newTextGraphics() >> textGraphics

        when:
        gui.mainMenu(3)


        then:
            1 * screen.clear()
            1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
            1 * textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ')
            1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"))
            1 * textGraphics.fillRectangle(new TerminalPosition(0, 20), screen.getTerminalSize(), ' ')
            1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"))
            1 * textGraphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(46, 1), ' ')

            1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK)
            1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
            1 * textGraphics.putString(17, 3, "Flappy Bird", SGR.BOLD)

            1 * textGraphics.putString(20, 7, "Play", SGR.BLINK)
            1 * textGraphics.putString(18, 10, "Scoreboard")
            1 * textGraphics.putString(20, 13, "Quit")

            1 * screen.refresh()

    }

    def 'pauseMenu'(){
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        def textGraphics = Mock(TextGraphics.class)

        screen.newTextGraphics() >> textGraphics

        when:
        gui.pauseMenu(0)

        then:
        1 * screen.clear()
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 20), screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(46, 1), ' ')
        1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.putString(19, 7, "Resume", SGR.BLINK);
        1 * textGraphics.putString(20, 10, "Quit");
    }

    def 'endMenu'(){
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        def textGraphics = Mock(TextGraphics.class)

        screen.newTextGraphics() >> textGraphics

        when:
        gui.endMenu(0)

        then:
        1 * screen.clear();
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));
        1 * textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ');
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"));
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 20), screen.getTerminalSize(), ' ');
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"));
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(46, 1), ' ');

        1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"));

        1 * textGraphics.putString(18, 7, "Game Over", SGR.BOLD);
        1 * textGraphics.putString(18, 10, "Score:");
        1 * textGraphics.putString(24, 10, String.valueOf(0));

        1 * screen.refresh();
    }
}