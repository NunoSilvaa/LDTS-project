import Rectangle.*
import Controls.*

import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class BirdTest extends Specification{
    private Position position
    private Dimension dimension
    private Bird bird
    private TextGraphics screen;
    private boolean result;

    def setup(){
        position = new Position(20,20)
        dimension = new Dimension(10,10)
        bird = new Bird(position, dimension, 2, 3)
        screen = Mock(TextGraphics)
    }

    def"Gravity test"(){
        when:
        result = bird.update(30);

        then:
        bird.getPosition() == new Position(20, 22)
        result == false
    }

    def"Gravity test limits"(){
        when:
        for(int i = 0; i < 11; i++)
            result = bird.update(30);

        then:
        bird.getPosition() == new Position(20, 30)
        result == true
    }

    def"Slap test limits"(){
        when:
        for(int i = 0; i < 11; i++)
            bird.slap(0)

        then:
        bird.getPosition() == new Position(20, 0)
    }

    def"Slap test"(){
        when:
        bird.slap(0)

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
}
