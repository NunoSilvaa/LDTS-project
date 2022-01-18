package gameTest

import flappyBird.game.*

import spock.lang.Specification
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

class GameTest extends Specification{


    def"Draw test"(){
        given:
        def gui = Mock()
        def screen = Mock(Screen.class)
        def game = new Game()
        def arena = Mock(Arena.class)

        game.setArena(arena)
        game.setScreen(screen)

        when:
        game.draw()

        then:
        1*screen.clear()
        1*arena.draw(_)
        1*screen.refresh()

    }

    def"Run test - with input"(){
        given:
        Terminal terminal = Mock()
        when:
        game.run()
        then:
        1*terminal.pollInput()
    }


}
