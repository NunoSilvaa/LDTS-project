package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.move.Diagonal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
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
        given:
        def bird = new Bird(new Position(10,10), new Dimension(10,10), 2, new Vertical(),3 ,3)
        def r1 = Mock(Rectangle)
        bird.setRectangle(r1);
        def weapon = Mock(Sting)
        def r2 = Mock(Rectangle)
        bee.setRectangle(r2)
        bee.setWeapon(weapon)
        r2.intersect(r1) >> true

        when:
        bee.attack(bird)

        then:
        1 * weapon.attackBird(_)
    }

    def"Attack Bird-(False)"(){
        given:
        def bird = new Bird(new Position(10,10), new Dimension(10,10), 2, new Vertical(),3 ,3)
        def r1 = Mock(Rectangle)
        bird.setRectangle(r1);
        def weapon = Mock(Sting)
        def r2 = Mock(Rectangle)
        bee.setRectangle(r2)
        bee.setWeapon(weapon)
        r2.intersect(r1) >> false

        when:
        bee.attack(bird)

        then:
        0 * weapon.attackBird(_)
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