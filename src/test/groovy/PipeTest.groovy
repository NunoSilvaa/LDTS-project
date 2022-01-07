import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PipeTest extends Specification{
    private Position position
    private Dimension dimension
    private Pipe pipe
    private TextGraphics screen;

    def setup(){
        position = new Position(20,20)
        dimension = new Dimension(10,10)
        pipe = new Pipe(position, dimension, 2)
        screen = Mock(TextGraphics)
    }

    def"Update test"(){
        when:
            pipe.update()

        then:
            pipe.getPosition() == new Position(18, 20)
    }

    def"Draw test"(){
        when:
            pipe.draw(screen)

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_,_,_)

    }

    def"Collision Test - should not collide"(){
        pipe:
            pipe.overlap(pos) == bool

        where:
        pos | bool
        new Position(25,20) | false
        new Position(25,15) | false
        new Position(20,20) | true
        new Position(17,20) | true
        new Position(17,17) | true
    }

}
