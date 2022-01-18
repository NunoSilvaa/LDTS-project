package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Invincible
import flappyBird.entities.powerups.Life
import flappyBird.game.Arena
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.entities.Bird
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class LifeTest extends Specification {
    private Life life
    private Life specialLife

    void setup() {
        life = new Life(new Position(25, 25), new Dimension(10, 10), 2, new Vertical())
        specialLife = Mock(Life.class)
    }


    def "Draw"() {
        given:
        def screen = Mock(TextGraphics)

        when:
        life.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_, _, _)

    }


    def "Effect "() {
        def bird = Mock(Bird)
        def arena = Spy(Arena, constructorArgs: [42, 42, bird])

        when:
        life.effect(arena)

        then:
        1 * bird.increaseLives(_)

    }
}