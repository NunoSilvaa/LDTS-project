package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.Bird
import flappyBird.entities.enemies.Bee
import flappyBird.entities.powerups.*
import flappyBird.entities.weapons.Sting
import flappyBird.game.Arena
import flappyBird.move.Diagonal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class FasterTest extends Specification{
    private Faster faster
    private Faster specialFaster

    void setup(){
        faster = new Faster(new Position(25,25), new Dimension(10,10),2, new Vertical())
        specialFaster = Mock(Faster.class)
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
        def arena = Spy(Arena, constructorArgs:[42,42])

        when:
        faster.effect(arena)

        then:
        1 * arena.setState(_)
    }


}
