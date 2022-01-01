import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class BirdTest extends Specification{
    def"Gravity test"(){
        given:
            Position position = new Position(20,20,2)
            Dimension dimension = new Dimension(10,10)
            Bird bird = new Bird(position, dimension)

        when:
            bird.update();

        then:
            bird.getPosition() == new Position(20, 22, 2)
    }

    def"Slap test"(){
        given:
        Position position = new Position(20,20,2)
        Dimension dimension = new Dimension(10,10)
        Bird bird = new Bird(position, dimension, 3)

        when:
        bird.slap();

        then:
        bird.getPosition() == new Position(20, 17, 2)
    }

    def"Draw test"(){
        given:
        Position position = new Position(20,20,2)
        Dimension dimension = new Dimension(10,10)
        Bird bird = new Bird(position, dimension)
        def screen = Mock(TextGraphics)

        when:
        bird.draw()

        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_)

    }
}
