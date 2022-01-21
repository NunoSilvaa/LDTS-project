package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.powerups.Health
import flappyBird.entities.powerups.Life
import flappyBird.game.Arena
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.entities.Bird
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class LifeTest extends Specification {
    private Life life

    void setup() {
        life = new Life(new Position(25, 25), new Dimension(10, 10), 2, new Vertical())
    }


    def

    "Draw"() {
        given:
        def screen = Mock(TextGraphics)

        when:
        life.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_, _, _)

    }


    def"Effect"(){
        def rBird = Mock(Rectangle.class)
        def bird = Spy(Bird, constructorArgs: [rBird, 2,new Vertical(),2,2])
        def arena = new Arena(60,30, bird)
        def rHealth = Mock(Rectangle.class)
        def specialHealth = new Health(rHealth,1, new Vertical())
        arena.injectPowerUps(specialHealth)
        rBird.intersect(rHealth) >> true

        when:
        specialHealth.effect(arena)

        then:
        1 * bird.setHealth(100)
    }
}