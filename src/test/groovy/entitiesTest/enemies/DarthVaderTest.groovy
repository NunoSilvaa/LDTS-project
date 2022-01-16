package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class DarthVaderTest extends Specification{
    def"Create Weapon"(){
        given:
        def darthVader = Mock(DarthVader)

        expect:
        LaserSword in darthVader.getWeapon()
    }

    def"Draw"(){
        given:
        def darthVader = Mock(DarthVader)
        def screen = Mock(TextGraphics)

        when:
        darthVader.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Attack Bird-(True)"(){
        given:
        def bird = Mock(Bird.class)
        def darthVader = Mock(DarthVader.class)
        bird.setHealth(100)
        bird.setLives(4)
        darthVader.intersect(bird) >> true

        when:
        darthVader.attack(bird)

        then:
        bird.getHealth() == 100
        bird.getLives() == 3

    }

    def"Attack Bird-(False)"(){
        given:
        def bird = Mock(Bird.class)
        def darthVader = Mock(DarthVader.class)
        bird.setHealth(100)
        bird.setLives(4)
        darthVader.intersect(bird) >> false

        when:
        darthVader.attack(bird)

        then:
        bird.getHealth() == 100
        bird.getLives() == 4

    }

    def"Update DarthVader"(){
        given:

        DarthVader  darthVader = new DarthVader(new Position(25,25),new Dimension(1,1), 2)

        when:
        boolean result = darthVader.update(0)

        then:
        darthVader.getPosition() == new Position(25, 23)
        result == false
    }

    def"Update DarthVader  - Limits"(){
        given:

        DarthVader  darthVader = new DarthVader(new Position(25,25),new Dimension(1,1), 2)
        boolean result

        when:
        for(int i = 0; i < 15; i++)
            result = darthVader.update(0)

        then:
        result == true
    }
}