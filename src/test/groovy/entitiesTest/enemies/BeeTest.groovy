package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class BeeTest extends Specification{
    def"Create Weapon"(){
        given:
        def bee = Mock(Bee)

        expect:
        Sting in bee.getWeapon()
    }

    def"Draw"(){
        given:
        def bee = Mock(Bee)
        def screen = Mock(TextGraphics)

        when:
        bee.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Attack Bird-(True)"(){
        given:
        def bird = Mock(Bird.class)
        def bee = Mock(Bee.class)
        bird.setHealth(100)
        bird.setLives(4)
        bee.intersect(bird) >> true

        when:
        bee.attack(bird)

        then:
        bird.getHealth() == 75
        bird.getLives() == 4

    }

    def"Attack Bird-(False)"(){
        given:
        def bird = Mock(Bird.class)
        def bee = Mock(Bee.class)
        bird.setHealth(100)
        bird.setLives(4)
        bee.intersect(bird) >> false

        when:
        bee.attack(bird)

        then:
        bird.getHealth() == 100
        bird.getLives() == 4

    }

    def"Update Bee"(){
        given:

        Bee  bee = new Bee(new Position(25,25),new Dimension(1,1), 2)

        when:
        boolean result = bee.update(0)

        then:
        bee.getPosition() == new Position(23, 23)
        result == false
    }

    def"Update Bee - Limits"(){
        given:

        Bee  bee = new Bee(new Position(25,25),new Dimension(1,1), 2)
        boolean result

        when:
        for(int i = 0; i < 15; i++)
            result = bee.update(30);

        then:
        result == true
    }
}
