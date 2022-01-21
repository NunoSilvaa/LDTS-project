package gameTest.arenaStatesTests

import flappyBird.entities.Bird
import flappyBird.entities.pipes.Pipe
import flappyBird.game.Arena
import flappyBird.game.states.FasterState
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class FasterStateTest extends Specification{
    def"Faster State Test"(){
        given:
        def bird = new Bird(new Position(10,10), new Dimension(10,10), 1, new Vertical(), 1, 1)
        def arena = new Arena(42,42,bird)
        def pipe = new Pipe(new Position(15,15), new Dimension(10,10),2,new Horizontal())
        arena.injectPipe(pipe)
        def arenaState = new FasterState(arena)
        when:
        arena.setState(arenaState)
        then:
        bird.getSpeed() == 1
        pipe.getSpeed() == 3
    }
}
