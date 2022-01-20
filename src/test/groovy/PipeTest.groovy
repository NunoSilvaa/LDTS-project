package entitiesTest

import flappyBird.entities.observer.EntitiesObserver
import flappyBird.entities.pipes.Pipe
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.*
import flappyBird.entities.*

import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PipeTest extends Specification{
    private def topPipe
    private def bottomPipe
    private Pipe pipe
    private def screen;

    def setup(){
        topPipe = new Pipe(new Position(20,0), new Dimension(10,10), 2, new Horizontal())
        bottomPipe = new Pipe(new Position(20,30), new Dimension(10,10), 2, new Horizontal())
        pipe = new Pipe(new Position(10,10), new Dimension(10,10), 2, new Horizontal())
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

    def"Bird Collide - True"(){
        given:
        Rectangle r1 = Mock(Rectangle)
        Rectangle r2 = Mock(Rectangle)
        def bird = Spy(Bird, constructorArgs:[r1, 1, new Vertical(), 1, 1])
        pipe.setRectangle(r2)
        r2.intersect(r1)>>true

        when:
        pipe.collideBird(bird)

        then:
        1 * bird.decreaseLives(1)
    }

    def"Bird Collide - False"(){
        given:
        Rectangle r1 = Mock(Rectangle)
        Rectangle r2 = Mock(Rectangle)
        def bird = Spy(Bird, constructorArgs:[r1, 1, new Vertical(), 1, 1])
        pipe.setRectangle(r2)
        r2.intersect(r1)>>false

        when:
        pipe.collideBird(bird)

        then:
        0 * bird.decreaseLives(1)

    }

    def"Increase Speed"(){
        when:
        bottomPipe.increaseSpeed(2)
        then:
        bottomPipe.getSpeed() == 4
    }

    def"Add EntityObserver"(){
        given:
        def observer = Mock(EntitiesObserver)
        when:
        int begin = bottomPipe.getNumObserver()
        bottomPipe.addObserver(observer)
        int end = bottomPipe.getNumObserver()
        then:
        end - begin == 1
    }

}

