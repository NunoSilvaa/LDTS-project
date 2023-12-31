package flappyBird.controls.gameStates

import flappyBird.MusicPlayer
import flappyBird.game.*
import flappyBird.controls.gameStates.*
import flappyBird.menus.*
import flappyBird.move.Vertical
import spock.lang.Specification

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType


class GameStateTest extends Specification{

    def 'rangeLimiter'(){
        def game = Mock(Game)
        def gameState = new InitialGameState(game)

        expect:
        gameState.rangeLimiter(4) == 3
        gameState.rangeLimiter(2) == 2
        gameState.rangeLimiter(-1) == 2

    }

    def 'processOption (ArrowDown)'(){
        given:
        def gui = Mock(Gui.class)
        def game = Mock(Game)
        def option = 3
        def k = Mock(KeyStroke.class)
        def gameState = Mock(InitialGameState.class)
        gameState.setGame(game)
        gui.getKeyStroke() >> k
        k.getKeyType() >> KeyType.ArrowDown >> KeyType.Enter
        gameState.rangeLimiter(3) >> 2

        when:
        gameState.processOption(gui)

        then:
        option - 1 == 2
        gui.mainMenu(option)
    }

    def 'processOption (ArrowUP)'() {
        given:
        def gui = Mock(Gui.class)
        def game = Mock(Game)
        def option = 2
        def k = Mock(KeyStroke.class)
        def gameState = Mock(InitialGameState.class)
        gameState.setGame(game)
        gui.getKeyStroke() >> k
        k.getKeyType() >> KeyType.ArrowUp >> KeyType.Enter

        when:
        gameState.processOption(gui)

        then:
        option + 1 == 3
        gui.mainMenu(option)

    }

    def 'InitialGameState'(){
        given:
        def game = Mock(Game.class)
        def gui = Mock(Gui.class)
        def gameState = Mock(InitialGameState.class)
        gameState.setGame(game)
        gameState.processOption(gui) >> 3

        when:

        gameState.execute(gui)

        then:
        1 * gameState.(_)
    }

    def 'GameStateRun'(){
        given:
        def game = Mock(Game.class)
        def gui = Mock(Gui.class)
        def gameState = new GameStateRun()
        def score = 0
        MusicPlayer sound = Mock(MusicPlayer)
        gameState.setGame(game)

        when:
        gameState.execute(gui)

        then:
        game.setRunning(true)
        game.draw()
        game.setScore(score)
        game.changeGameState((new GameStateEnd().setSound(sound)))
    }

    def 'GameStateEnd'(){
        given:
        def game = Mock(Game.class)
        def gui = Mock(Gui.class)
        def gameState = new GameStateEnd()
        def score = 0;
        gameState.setGame(game)

        when:
        gameState.execute(gui)

        then:
        gui.endMenu(score)
    }

}
