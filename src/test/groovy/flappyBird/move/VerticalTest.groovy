package flappyBird.move

import flappyBird.entities.pipes.Pipe
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class VerticalTest extends Specification{
    def"Vertical"(){
        given:
        Pipe entity = new Pipe(new Position(10,10), new Dimension(10,10),1, new Vertical())

        when:
        entity.move()

        then:
        entity.getPosition() == new Position(10,11)
    }
}
