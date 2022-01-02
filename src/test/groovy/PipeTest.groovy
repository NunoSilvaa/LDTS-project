import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PipeTest extends Specification{
    private Position position
    private Dimension dimension
    private Pipe pipe
    private TextGraphics screen;
    private boolean result

    def setup(){
        position = new Position(20,20)
        dimension = new Dimension(10,10)
        pipe = new Pipe(position, dimension, 2)
        screen = Mock(TextGraphics)
    }

    def"Update test limits"(){
        when:
        for(int i = 0; i < 11; i++)
            result = pipe.update(0);

        then:
            pipe.getPosition() == new Position(0, 20)
            result == true
    }

    def"Update test"(){
        when:
            result = pipe.update(0)

        then:
            pipe.getPosition() == new Position(18, 20)
            result == false
    }

    def"Draw test"(){
        when:
            pipe.draw(screen)

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_,_,_)

    }
}
