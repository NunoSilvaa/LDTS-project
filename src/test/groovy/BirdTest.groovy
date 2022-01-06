import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class BirdTest extends Specification{
    private Position position
    private Dimension dimension
    private Bird bird
    private TextGraphics screen;

    def setup(){
        position = new Position(20,20)
        dimension = new Dimension(10,10)
        bird = new Bird(position, dimension, 2, 3)
        screen = Mock(TextGraphics)
    }

    def"Gravity test"(){
        when:
            bird.update();

        then:
            bird.getPosition() == new Position(20, 22)
    }

    def"Slap test"(){
        when:
            bird.slap();

        then:
            bird.getPosition() == new Position(20, 17)
    }

    def"Draw test"(){
        when:
            bird.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)
    }

    def"Death test"(){
        expect:
        bird.isDead() == false

    }
}
