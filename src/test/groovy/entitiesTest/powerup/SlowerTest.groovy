package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Bird
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Slower
import flappyBird.game.Arena
import flappyBird.game.states.SlowerState
import flappyBird.move.Vertical
import flappyBird.rectangle.*
import spock.lang.Specification

class SlowerTest extends Specification {
    private Slower slower

    void setup(){
        slower = new Slower(new Position(25,25), new Dimension(10,10),2, new Vertical())
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
        Rectangle rBird = Mock(Rectangle.class)
        def bird = new Bird(rBird, 1, new Vertical(),1,1)
        def arena = new Arena(60,30, bird)
        def rSlower = Mock(Rectangle.class)
        def specialSlower = new Slower(rSlower,1, new Vertical())
        arena.injectPowerUps(specialSlower)
        rBird.intersect(rSlower) >> true

        when:
        specialSlower.effect(arena)

        then:
        arena.getState() instanceof SlowerState
    }
}
