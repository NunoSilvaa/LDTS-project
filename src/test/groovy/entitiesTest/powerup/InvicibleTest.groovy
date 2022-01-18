package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Invincible
import flappyBird.game.Arena
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class InvicibleTest extends Specification {
    private Invincible invincible
    private Invincible specialInvincible

    void setup(){
        invincible = new Invincible(new Position(25,25), new Dimension(10,10),2, new Vertical())
        specialInvincible = Mock(Invincible.class)
    }


    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        faster.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Effect"(){
        def arena = Spy(Arena, constructorArgs:[42, 42])

        when:
        invincible.effect(arena)

        then:
        1 * arena.setState(_)
    }
}
