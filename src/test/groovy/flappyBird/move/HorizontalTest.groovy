package flappyBird.move

import flappyBird.entities.pipes.Pipe
import flappyBird.move.Horizontal
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class HorizontalTest extends Specification{
    def"Horizontal"(){
        given:
        Pipe entity = new Pipe(new Position(10,10), new Dimension(10,10),1, new Horizontal())

        when:
        entity.move()
        then:
        entity.getPosition() == new Position(9,10)
    }
}
