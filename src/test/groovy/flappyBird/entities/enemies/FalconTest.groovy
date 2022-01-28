package flappyBird.entities.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.MusicPlayer
import flappyBird.entities.observer.EntitiesObserver
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.move.Diagonal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class FalconTest extends Specification{
    private Falcon falcon
    private Falcon specialFalcon

    void setup(){
        falcon = new Falcon(new Position(25,25), new Dimension(10,10),2, new Diagonal())
        specialFalcon = Mock(Falcon.class)
        MusicPlayer sound = Mock(MusicPlayer)
        falcon.setSound(sound)
    }

    def"Create Weapon"(){
        when:
        def weapon = falcon.getWeapon()

        then:
        weapon instanceof Bite
    }

    def"Draw"(){
        given:
        def screen = Mock(TextGraphics)

        when:
        falcon.draw(screen)

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
        falcon.setRectangle(r2)
        falcon.setWeapon(weapon)
        r2.intersect(r1) >> true

        when:
        falcon.attack(bird)

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
        falcon.setRectangle(r2)
        falcon.setWeapon(weapon)
        r2.intersect(r1) >> false

        when:
        falcon.attack(bird)

        then:
        0 * weapon.attackBird(_)
    }

    def"Update"(){
        when:
        falcon.move()

        then:
        falcon.getPosition() == new Position(23, 27)
    }

    def"Increase Speed"(){
        when:
        falcon.increaseSpeed(2)
        then:
        falcon.getSpeed() == 4
    }

    def"Add EntityObserver"(){
        given:
        def observer = Mock(EntitiesObserver)
        when:
        int begin = falcon.getNumObserver();
        falcon.addObserver(observer)
        int end = falcon.getNumObserver();
        then:
        end - begin == 1
    }

}