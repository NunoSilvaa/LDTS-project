import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PipeTest extends Specification{
    def given(){
        Position position = new Position(20,20,2)
        Dimension dimension = new Dimension(10,10)
        Pipe pipe = new Pipe(position, dimension)
        def screen = Mock(TextGraphics)
    }

    def"Update test"(){
        when:
            pipe.update()

        then:
            pipe.getPosition() == new Position(18, 20, 2)
    }

    def"Draw test"(){
        when:
            pipe.draw()

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_)

    }
}
