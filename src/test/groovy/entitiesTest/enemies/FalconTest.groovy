package entitiesTest.enemies

import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.entities.enemies.*
import flappyBird.entities.weapons.*
import flappyBird.entities.*
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import spock.lang.Specification

class  FalconTest extends Specification{
    def"Create Weapon"(){
        given:
        def  falcon = Mock(Falcon.class)

        expect:
        Bite in falcon.getWeapon()
    }

    def"Draw"(){
        given:
        def falcon = Mock(Falcon.class)
        def screen = Mock(TextGraphics)

        when:
        falcon.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Attack Bird-(True)"(){
        given:
        def  falcon = Mock(Falcon.class)
        def  bird = Mock(Bird.class)
        bird.setHealth(100)
        bird.setLives(4)
        falcon.intersect(bird) >> true

        when:
        falcon.attack(bird)

        then:
        bird.getHealth() == 75
        bird.getLives() == 4

    }

    def"Attack Bird-(False)"(){
        given:
        def bird = Mock(Bird.class)
        def  falcon = Mock(Falcon.class)
        bird.setHealth(100)
        bird.setLives(4)
        falcon.intersect(bird) >> false

        when:
        falcon.attack(bird)

        then:
        bird.getHealth() == 100
        bird.getLives() == 4

    }

    def"Update Falcon"(){
        given:

        Falcon falcon = new Falcon(new Position(25,25),new Dimension(1,1), 2)

        when:
        boolean result = falcon.update(30)

        then:
        falcon.getPosition() == new Position(23, 27)
        result == false
    }

    def"Update Falcon - Limits"(){
        given:

        Falcon falcon = new Falcon(new Position(25,25),new Dimension(1,1), 2)
        boolean result

        when:
        for(int i = 0; i < 15; i++)
            result = falcon.update(30)

        then:
        result == true
    }
}