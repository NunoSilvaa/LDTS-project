package entitiesTest.powerup

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.MusicPlayer
import flappyBird.entities.Bird
import flappyBird.entities.powerups.*
import flappyBird.game.Arena
import flappyBird.game.states.FasterState
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class FasterTest extends Specification{
    private Faster faster

    void setup(){
        faster = new Faster(new Position(25,25), new Dimension(10,10),2, new Vertical())
        MusicPlayer sound = Mock(MusicPlayer)
        faster.setSound(sound)
    }


    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        faster.draw(screen)

        then:
        1 * screen.setForegroundColor(_)
        then:
        1 * screen.putString(_,_,_)

    }


    def"Effect"(){
        Rectangle rBird = Mock(Rectangle.class)
        def bird = new Bird(rBird, 1, new Vertical(),1,1)
        def arena = new Arena(60,30, bird)
        def rFaster = Mock(Rectangle.class)
        def specialFaster = new Faster(rFaster,1, new Vertical())
        arena.injectPowerUps(specialFaster)
        rBird.intersect(rFaster) >> true

        when:
        specialFaster.effect(arena)

        then:
        arena.getState() instanceof FasterState
    }


}
