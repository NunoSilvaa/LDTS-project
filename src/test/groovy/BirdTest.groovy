import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class BirdTest extends Specification{
    def given(){
        Position position = new Position(20,20,2)
        Dimension dimension = new Dimension(10,10)
        Bird bird = new Bird(position, dimension, 3)
        def screen = Mock(TextGraphics)
    }

    def"Gravity test"(){
        when:
            bird.update();

        then:
            bird.getPosition() == new Position(20, 22, 2)
    }

    def"Slap test"(){
        when:
            bird.slap();

        then:
            bird.getPosition() == new Position(20, 17, 2)
    }

    def"Draw test"(){
        when:
            bird.draw()

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_)

    }
}