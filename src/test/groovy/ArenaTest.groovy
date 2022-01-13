import Controls.Arena
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class ArenaTest extends Specification{


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

        pipe1.overlap(_,_) >> false
        pipe2.overlap(_,_) >> true

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

        pipe1.overlap(_,_) >> false
        pipe2.overlap(_,_) >> false

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
