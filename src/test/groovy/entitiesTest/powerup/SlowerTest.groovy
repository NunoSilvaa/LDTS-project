package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Slower
import flappyBird.game.Arena
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class SlowerTest extends Specification {
    private Slower slower
    private Slower specialSlower

    void setup(){
        slower = new Slower(new Position(25,25), new Dimension(10,10),2, new Vertical())
        specialSlower = Mock(Slower.class)
    }


    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        slower.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Effect"(){
        def arena = Spy(Arena, constructorArgs:[42, 42])

        when:
        slower.effect(arena)

        then:
        1 * arena.setState(_)
    }
}
