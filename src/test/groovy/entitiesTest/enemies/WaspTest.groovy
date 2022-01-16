package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class WaspTest extends Specification{
    def"Create Weapon"(){
        given:
        def wasp = Mock(Wasp)

        expect:
        Poison in wasp.getWeapon()
    }

    def"Draw"(){
        given:
        def wasp = Mock(Wasp)
        def screen = Mock(TextGraphics)

        when:
        wasp.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Attack Bird-(True)"(){
        given:
        def bird = Mock(Bird.class)
        def wasp = Mock(Wasp.class)
        bird.setHealth(100)
        bird.setLives(4)
        wasp.intersect(bird) >> true

        when:
        wasp.attack(bird)

        then:
        bird.getHealth() == 50
        bird.getLives() == 3

    }

    def"Attack Bird-(False)"(){
        given:
        def bird = Mock(Bird.class)
        def wasp = Mock(Wasp.class)
        bird.setHealth(100)
        bird.setLives(4)
        wasp.intersect(bird) >> false

        when:
        wasp.attack(bird)

        then:
        bird.getHealth() == 100
        bird.getLives() == 4

    }

    def"Update Wasp Test"(){
        given:

        Wasp  wasp = new Wasp(new Position(25,25),new Dimension(1,1), 2)

        when:
        boolean result = wasp.update(0)

        then:
        wasp.getPosition() == new Position(23, 25)
        result == false
    }

    def"Update Wasp  - Limits"(){
        given:

        Wasp  wasp = new Wasp(new Position(25,25),new Dimension(1,1), 2)
        boolean result

        when:
        for(int i = 0; i < 15; i++)
            result = wasp.update(0)

        then:
         result == true
    }
}