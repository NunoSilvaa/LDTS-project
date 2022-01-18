package gameTest

import flappyBird.entities.pipes.Pipe
import flappyBird.game.Arena
import flappyBird.entities.*
import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.game.states.ArenaState
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class ArenaTest extends Specification{
    private Arena arena;
    private TextGraphics screen;
    private ArenaState state;

    def setup(){
        arena = new Arena(42,42)
        screen = Mock(TextGraphics)
    }

    def"Instance"(){
        when:
    }

    def"Draw Test"(){
        given:
        def arena = new Arena(42,42)
        def screen = Mock(TextGraphics)

        when:
        arena.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_,_,_)
        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_,_,_)
        then:
        2 * screen.setBackgroundColor(_)
        2 * screen.fillRectangle(_,_,_)
    }

    def"Creating Pipes Test"(){
        given:
        def arena = new Arena(42,42)

        int start = arena.getNumberPipes();
        when:
        arena.createPipes()
        then:
        arena.getNumberPipes() - start==2
    }

    def"Collision of all Pipes Test(True)"(){
        given:
        def bird = Mock(Bird.class)
        def arena = new Arena(42,42,bird)

        def pipe1 = Mock(Pipe)
        def pipe2 = Mock(Pipe)

        bird.intersect(pipe1) >> false
        bird.intersect(pipe2) >> true

        arena.addPipe(pipe1)
        arena.addPipe(pipe2)

        expect:
        arena.verifyPipeCollisions() == true

    }


    def"Collision of all Pipes Test(False)"(){
        given:
        def bird = Mock(Bird.class)
        def arena = new Arena(42,42,bird)

        def pipe1 = Mock(Pipe)
        def pipe2 = Mock(Pipe)

        bird.intersect(pipe1) >> false
        bird.intersect(pipe2) >> false

        arena.addPipe(pipe1)
        arena.addPipe(pipe2)

        expect:
        arena.verifyPipeCollisions() == false

    }

    def"Update Test - slapping(True / Bird Alive)"() {
        given:
        boolean result
        def bird = Mock(Bird.class)
        def arena = new Arena(42, 42, bird)
        bird.isDead() >> false

        when:
        result = arena.update(true)

        then:
        1 * bird.slap(0)
        result == true

    }

    def"Update Test - slapping(True / Bird Dead)"() {
        given:
        boolean result
        def bird = Mock(Bird.class)
        def arena = new Arena(42, 42, bird)
        bird.isDead() >> true

        when:
        result = arena.update(true)

        then:
        1 * bird.slap(0)
        result == false

    }

    def"Update Test - slapping(False / Bird Alive)"() {
        given:
        boolean result
        def bird = Mock(Bird.class)
        def arena = new Arena(42, 42, bird)
        bird.isDead() >> false

        when:
        result = arena.update(false)

        then:
        1 * bird.update(_)
        result == true

    }

    def"Update Test - slapping(False / Bird Dead)"() {
        given:
        boolean result
        def bird = Mock(Bird.class)
        def arena = new Arena(42, 42, bird)
        bird.isDead() >> true

        when:
        result = arena.update(false)

        then:
        1 * bird.update(_)
        result == false

    }



}
