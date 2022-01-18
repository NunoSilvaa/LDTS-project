package entitiesTest

import flappyBird.entities.pipes.Pipe
import flappyBird.move.Horizontal
import flappyBird.rectangle.*
import flappyBird.entities.*

import flappyBird.entities.pipes.BottomPipe
import flappyBird.entities.pipes.TopPipe
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PipeTest extends Specification{
    private def topPipe
    private def bottomPipe
    private def screen;

    def setup(){
        topPipe = new TopPipe(new Position(20,0), new Dimension(10,10), 2, new Horizontal())
        bottomPipe = new BottomPipe(new Position(20,30), new Dimension(10,10), 2, new Horizontal())
        screen = Mock(TextGraphics)
    }

    def"Update test (Bottom Pipe)"(){
        when:
            bottomPipe.move()

        then:
            bottomPipe.getPosition() == new Position(18, 30)
    }

    def"Update test (Top Pipe)"(){
        when:
            topPipe.move()

        then:
            topPipe.getPosition() == new Position(18, 0)
    }

    def"Draw test(Bottom Pipe)"(){
        when:
            bottomPipe.draw(screen)

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_,_,_)

    }

    def"Draw test(Top Pipe)"(){
        when:
            topPipe.draw(screen)

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_,_,_)

    }

    def"Bird Collide"(){
        given:
        def specialPipe = Mock(Pipe.class)
        def specialBird = Mock(Bird.class)
        specialPipe.intersect(specialBird) >> true

        when:
        specialPipe.collideBird(specialBird)

        then:
        1 * specialBird.decreaseLives(_ as int)
    }

}

