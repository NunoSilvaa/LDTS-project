package controls

import Controls.Game
import Controls.GameStates.GameStateEnd
import Controls.GameStates.GameStateRun
import menus.Gui
import spock.lang.Specification

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class GameStateTest extends Specification{

    def 'rangeLimiter'(){
        /*def num = Integer

        when:
        num > 3*/

    }

    def 'processOption'(){
        given:
        def gui = Mock(Gui.class)
        def option = 3
        def k = Mock(KeyStroke.class)

        //gui.getKeyStroke() >> k

        k.getKeyType() >> KeyType.ArrowDown >> KeyType.Enter
        k.getKeyType() >> KeyType.ArrowUp >> KeyType.Enter
        gui.getKeyStroke() >> k

        when:
        k == KeyType.ArrowDown

        then:
        1 * option - 1
        //1 * rangeLimiter(option)
        //0 * gui.mainMenu(option)

        when:
        k == KeyType.ArrowUp

        then:
        1 * option + 1
        //1 * rangeLimiter(option)
        //0 * gui.mainMenu(option)
    }

    def 'InitialGameState'(){
        given:
        def game = Mock(Game.class)
        def gui = Mock(Gui.class)
        def k = Mock(KeyStroke.class)
        def process = Mock(InitialGameState.class)
        def initial = process(game)

        gui.getKeyStroke(_) >> k
        initial.processOption(gui) >> 3

        when:

        initial.execute(gui)

        then:
        1 * game.changeGameState(_)

    }

    def 'GameStateEnd'(){
        /*def game = Mock(Game.class)
        def gui = Mock(Gui.class)
        def end = new GameStateEnd(game)
        def k = Mock(KeyStroke.class)*/
    }

}
