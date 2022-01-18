package move

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Entities
import flappyBird.move.Diagonal
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class HorizontalTest extends Specification{
    def"Horizontal"(){
        given:
        Entities entity = new Entities(new Position(10,10), new Dimension(10,10),1, new Horizontal()) {
            @Override
            void draw(TextGraphics screen) {

            }
        }

        when:
        entity.move()
        then:
        entity.getPosition() == new Position(9,10)
    }
}
