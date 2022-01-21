package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.observer.EntitiesObserver
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class DarthVaderTest extends Specification{
    private DarthVader darthVader
    private DarthVader specialDarthVader

    void setup(){
        darthVader = new DarthVader(new Position(25,25), new Dimension(10,10),2, new Vertical())
        specialDarthVader = Mock(DarthVader.class)
    }

    def"Create Weapon"(){
        when:
        def weapon = darthVader.getWeapon()

        then:
        weapon instanceof LaserSword
    }

    def"Draw"(){
        given:
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
        def bird = new Bird(new Position(10,10), new Dimension(10,10), 2, new Vertical(),3 ,3)
        def r1 = Mock(Rectangle)
        bird.setRectangle(r1);
        def weapon = Mock(Sting)
        def r2 = Mock(Rectangle)
        darthVader.setRectangle(r2)
        darthVader.setWeapon(weapon)
        r2.intersect(r1) >> true

        when:
        darthVader.attack(bird)

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
        darthVader.setRectangle(r2)
        darthVader.setWeapon(weapon)
        r2.intersect(r1) >> false

        when:
        darthVader.attack(bird)

        then:
        0 * weapon.attackBird(_)
    }

    def"Update"(){
        when:
        darthVader.move()

        then:
        darthVader.getPosition() == new Position(25, 27)
    }

    def"Increase Speed"(){
        when:
        darthVader.increaseSpeed(2)
        then:
        darthVader.getSpeed() == 4
    }

    def"Add EntityObserver"(){
        given:
        def observer = Mock(EntitiesObserver)
        when:
        int begin = darthVader.getNumObserver();
        darthVader.addObserver(observer)
        int end = darthVader.getNumObserver();
        then:
        end - begin == 1
    }

}