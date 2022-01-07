import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import spock.lang.Specification
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

class GameTest extends Specification{
    private Game game
    private TextGraphics screen

    def setup(){
        game = new Game()
        screen = Mock(TextGraphics)
    }


    def"Constructor test - creating a terminal"(){
        given:
        DefaultTerminalFactory terminalFactory = Mock()
        when:
        new Game()
        then:
        1*terminalFactory.createTerminal(_)
    }

    def"Constructor test - creating an Arena"(){
        when:
        new Game()
        then:
        1*new Arena(42,42)
    }

    def"Draw test"(){
        given:
        Arena arena = Mock()

        when:
        game.draw()
        then:
        1*arena.draw(screen)

    }

    def"Run test - input is asked for"(){
        given:
        Terminal terminal = Mock()
        when:
        game.run()
        then:
        1*terminal.pollInput()
    }


}
