package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.move.Diagonal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class BeeTest extends Specification{
    private Bee bee
    private Bee specialBee

    void setup(){
        bee = new Bee(new Position(25,25), new Dimension(10,10),2, new Diagonal())
        specialBee = Mock(Bee.class)
    }

    def"Create Weapon"(){
        when:
        def weapon = bee.getWeapon()

        then:
        weapon instanceof Sting
    }

    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        bee.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Attack Bird-(True)"(){
    }

    def"Attack Bird-(False)"(){
        given:
        def bird = Mock(Bird.class)
        bird.setHealth(100)
        bird.setLives(4)
        specialBee.intersect(bird) >> false

        when:
        specialBee.attack(bird)

        then:
        bird.getHealth() == 100
        bird.getLives() == 4

    }

    def"Update Bee"(){
        when:
        bee.move()

        then:
        bee.getPosition() == new Position(23, 27)
    }

    def"Increase Speed"(){
        when:
        bee.increaseSpeed(2)
        then:
        bee.getSpeed() == 4
    }

    def"Add EntityObserver"(){
        given:
        def observer = Mock(EntitiesObserver)
        when:
        int begin = bee.getNumObserver();
        bee.addObserver(observer)
        int end = bee.getNumObserver();
        then:
        end - begin == 1
    }

}