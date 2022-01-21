package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Bird
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Health
import flappyBird.entities.powerups.Invincible
import flappyBird.game.Arena
import flappyBird.game.states.FasterState
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class HealthTest extends Specification{
    private Health health

    void setup(){
        health = new Health(new Position(25,25), new Dimension(10,10),2, new Vertical())
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
