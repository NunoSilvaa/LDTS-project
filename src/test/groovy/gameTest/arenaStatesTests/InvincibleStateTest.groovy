package gameTest.arenaStatesTests

import flappyBird.entities.Bird
import flappyBird.entities.pipes.Pipe
import flappyBird.game.Arena
import flappyBird.game.states.FasterState
import flappyBird.game.states.InvincibleState
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class InvincibleStateTest extends Specification{
    def"Invincible State Test"(){
        given:
        def rBird = Mock(Rectangle)
        def bird = new Bird(rBird, 1, new Vertical(), 1, 2)
        def arena = new Arena(42,42,bird)
        def rPipe = Mock(Rectangle)
        def pipe = new Pipe(rPipe,2,new Horizontal())
        arena.injectPipe(pipe)

        rPipe.intersect(rBird) >> true

        def arenaState = new InvincibleState(arena)
        when:
        arena.setState(arenaState)
        then:
        bird.getLives() == 2
    }
}
