package move

import flappyBird.entities.Entities
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Position
import spock.lang.Specification

class HorizontalTest extends Specification{
    def"Horizontal"(){
        given:
        def entity = Mock(Entities.class)
        def move = new Horizontal()
        entity.setSpeed(1)
        entity.setPosition(new Position(10,10))

        when:
        move.update(entity)

        then:
        entity.getPosition() == new Position(9,11)
    }
}
