import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class ArenaTest extends Specification{
    private Arena arena
    private TextGraphics screen

    def setup(){
        arena = new Arena(42,42)
        screen = Mock(TextGraphics)
    }

    def"Draw Test"(){
        when:
        arena.draw(screen)
        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_,_,_)
        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_,_,_)
        then:
        screen.setBackgroundColor(_)
        screen.fillRectangle(_,_,_)
    }

    def"Creating Pipes Test"(){
        given:
        int start = arena.getNumberPipes();
        when:
        arena.createPipes()
        then:
        arena.getNumberPipes() - start==2
    }

    def"Collision of all Pipes Test"(){


    }

    def"Update Test - slapping "(){
        given:
        Bird bird = Mock();
        when:
        arena.update(a)
        then:
        b * bird.slap(0)
        where:
        a | b
        true | 1
        false | 0
        false | 0
        true | 1
    }

    def"Verifying Pipe Collisions"(){
        given:
        Pipe pipe = Mock()
        when:
        arena.verifyPipeCollisions()
        then:
        2 * pipe.overlap()
    }


}
