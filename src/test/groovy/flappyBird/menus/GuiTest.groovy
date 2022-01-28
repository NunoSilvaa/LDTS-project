package flappyBird.menus


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
        def gui = new Gui(screen)

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

    def 'mainMenu(option3)'(){
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
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), new TerminalSize(60, 1), ' ')

        1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK)
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.putString(23, 3, "Flappy Bird", SGR.BOLD)

        1 * textGraphics.putString(26, 7, "Play", SGR.BLINK)
        1 * textGraphics.putString(26, 10, "Quit")

        1 * screen.refresh()

    }

    def 'mainMenu(option2)'(){
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        def textGraphics = Mock(TextGraphics.class)

        screen.newTextGraphics() >> textGraphics

        when:
        gui.mainMenu(2)


        then:
        1 * screen.clear()
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), new TerminalSize(60, 1), ' ')

        1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK)
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.putString(23, 3, "Flappy Bird", SGR.BOLD)

        1 * textGraphics.putString(26, 7, "Play")
        1 * textGraphics.putString(26, 10, "Quit", SGR.BLINK)

        1 * screen.refresh()

    }

    def 'pauseMenu(option3)'(){
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        def textGraphics = Mock(TextGraphics.class)

        screen.newTextGraphics() >> textGraphics

        when:
        gui.pauseMenu(3)

        then:
        1 * screen.clear()
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), new TerminalSize(60, 1), ' ')
        1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.putString(25, 7, "Resume", SGR.BLINK);
        1 * textGraphics.putString(26, 10, "Quit");
    }

    def 'pauseMenu(option2)'(){
        given:
        def screen = Mock(Screen.class)
        def gui = new Gui(screen)
        def textGraphics = Mock(TextGraphics.class)

        screen.newTextGraphics() >> textGraphics

        when:
        gui.pauseMenu(2)

        then:
        1 * screen.clear()
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#E0D796"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), screen.getTerminalSize(), ' ')
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71BF2E"))
        1 * textGraphics.fillRectangle(new TerminalPosition(0, 25), new TerminalSize(60, 1), ' ')
        1 * textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        1 * textGraphics.setBackgroundColor(TextColor.Factory.fromString("#71C5CF"))
        1 * textGraphics.putString(25, 7, "Resume");
        1 * textGraphics.putString(26, 10, "Quit", SGR.BLINK);
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
        1 * screen.clear()
        1 * textGraphics.setBackgroundColor(_);
        1 * textGraphics.fillRectangle(_, _, _)
        1 * textGraphics.setBackgroundColor(_)
        1 * textGraphics.fillRectangle(_, _, _)
        1 * textGraphics.setBackgroundColor(_)
        1 * textGraphics.fillRectangle(_, _, _)

        1 * textGraphics.setForegroundColor(_)
        1 * textGraphics.setBackgroundColor(_)

        1 * textGraphics.putString(_, _, _, _)
        1 * textGraphics.putString(_, _, _)
        1 * textGraphics.putString(_, _, _)

        1 * screen.refresh()
    }
}