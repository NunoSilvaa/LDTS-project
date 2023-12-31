package gameTest.arenaStatesTests

import flappyBird.entities.Bird
import flappyBird.entities.pipes.Pipe
import flappyBird.game.Arena
import flappyBird.game.states.SlowerState
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class SlowerStateTest extends Specification{
    def"Slower State Test"(){
        given:
        def bird = new Bird(new Position(10,10), new Dimension(10,10), 2, new Vertical(), 1, 1)
        def arena = new Arena(42,42,bird)
        def pipe = new Pipe(new Position(15,15), new Dimension(10,10),3,new Horizontal())
        arena.injectPipe(pipe)
        def arenaState = new SlowerState(arena)
        when:
        arena.setState(arenaState)
        then:
        bird.getSpeed() == 2
        pipe.getSpeed() == 1
    }
}