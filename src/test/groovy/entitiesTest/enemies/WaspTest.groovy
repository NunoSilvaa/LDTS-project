package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.MusicPlayer
import flappyBird.entities.enemies.*
import flappyBird.entities.observer.EntitiesObserver
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class WaspTest extends Specification{
    private Wasp wasp
    private Wasp specialWasp

    void setup(){
        wasp = new Wasp(new Position(25,25), new Dimension(10,10),2, new Horizontal())
        specialWasp = Mock(Wasp.class)
        MusicPlayer sound = Mock(MusicPlayer)
        wasp.setSound(sound)
    }

    def"Create Weapon"(){
        when:
        def weapon = wasp.getWeapon()

        then:
        weapon instanceof Poison
    }

    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        wasp.draw(screen)

        then:
        1 * screen.setForegroundColor(_)
        then:
        1 * screen.putString(_,_,_)

    }

    def"Attack Bird-(True)"(){
        given:
        def bird = new Bird(new Position(10,10), new Dimension(10,10), 2, new Vertical(),3 ,3)
        def r1 = Mock(Rectangle)
        bird.setRectangle(r1);
        def weapon = Mock(Sting)
        def r2 = Mock(Rectangle)
        wasp.setRectangle(r2)
        wasp.setWeapon(weapon)
        r2.intersect(r1) >> true

        when:
        wasp.attack(bird)

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
        wasp.setRectangle(r2)
        wasp.setWeapon(weapon)
        r2.intersect(r1) >> false

        when:
        wasp.attack(bird)

        then:
        0 * weapon.attackBird(_)
    }

    def"Update"(){
        when:
        wasp.move()

        then:
        wasp.getPosition() == new Position(23, 25)
    }

    def"Increase Speed"(){
        when:
        wasp.increaseSpeed(2)
        then:
        wasp.getSpeed() == 4
    }

    def"Add EntityObserver"(){
        given:
        def observer = Mock(EntitiesObserver)
        when:
        int begin = wasp.getNumObserver();
        wasp.addObserver(observer)
        int end = wasp.getNumObserver();
        then:
        end - begin == 1
    }
}