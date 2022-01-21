package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Bird
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Invincible
import flappyBird.entities.powerups.Slower
import flappyBird.game.Arena
import flappyBird.game.states.InvincibleState
import flappyBird.game.states.SlowerState
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class InvincibleTest extends Specification {
    private Invincible invincible

    void setup(){
        invincible = new Invincible(new Position(25,25), new Dimension(10,10),2, new Vertical())
    }


    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        invincible.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Effect"(){
        Rectangle rBird = Mock(Rectangle.class)
        def bird = new Bird(rBird, 1, new Vertical(),1,1)
        def arena = new Arena(60,30, bird)
        def rInvincible = Mock(Rectangle.class)
        def specialInvincible = new Invincible(rInvincible,1, new Vertical())
        arena.injectPowerUps(specialInvincible)
        rBird.intersect(rInvincible) >> true

        when:
        specialInvincible.effect(arena)

        then:
        arena.getState() instanceof InvincibleState
    }
}
