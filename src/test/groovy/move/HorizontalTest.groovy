package move

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Entities
import flappyBird.entities.pipes.Pipe
import flappyBird.move.Diagonal
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
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
