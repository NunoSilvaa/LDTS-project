package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Bird
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Health
import flappyBird.entities.powerups.Invincible
import flappyBird.game.Arena
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class HealthTest extends Specification{
    private Health health
    private Health specialHealth

    void setup(){
        health = new Health(new Position(25,25), new Dimension(10,10),2, new Vertical())
        specialHealth = Mock(Health.class)
    }


    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        health.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Effect "(){
        def bird = Mock(Bird)
        def arena = Spy(Arena, constructorArgs:[42, 42, bird])

        when:
        health.effect(arena)

        then:
        1 * bird.setHealth(100)
    }

}
