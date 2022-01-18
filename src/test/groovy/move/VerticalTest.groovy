package move

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Entities
import flappyBird.move.Diagonal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class VerticalTest extends Specification{
    def"Vertical"(){
        given:
        Entities entity = new Entities(new Position(10,10), new Dimension(10,10),1, new Vertical()) {
            @Override
            void draw(TextGraphics screen) {

            }
        }

        when:
        entity.move()

        then:
        entity.getPosition() == new Position(10,11)
    }
}
