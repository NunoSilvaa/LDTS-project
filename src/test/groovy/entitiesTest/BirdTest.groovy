package groovy.entitiesTest

import flappyBird.entities.Bird
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class BirdTest extends Specification{
    private Bird bird
    private TextGraphics screen;

    def setup(){
        bird = new Bird(new Position(20,20), new Dimension(10,10), 2, new Vertical(), 3, 5)
        screen = Mock(TextGraphics)
    }

    def"Decrease Health - 2"(){
        when:
        bird.decreaseHealth(110)
        then:
        bird.getLives() == 4
        bird.getHealth() == 100
    }

    def"Decrease Health - 3"(){
        when:
        bird.decreaseHealth(90)
        then:
        bird.getLives() == 5
        bird.getHealth() == 10
    }

    def"Gravity test"(){
        when:
        bird.move()

        then:
        bird.getPosition() == new Position(20,22)
    }

    def"Slap test"(){
        when:
        bird.slap()

        then:
        bird.getPosition() == new Position(20, 17)
    }

    def"Draw test"(){
        when:
        bird.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }
    def"Increase Lives"(){
        when:
        bird.increaseLives(3)
        then:
        bird.getLives() == 8
    }

    def"Decrease Lives"(){
        expect:
        bird.decreaseLives(lives)
        bird.getLives() == solutionLive
        bird.getHealth() == solutionHealth

        where:
        lives | solutionLive | solutionHealth
        1 | 4 | 100
        9 | 0 | 0
    }

    def"Decrease Lives - Also tests health"(){
        expect:
        bird.setHealth(40)
        bird.decreaseLives(lives)
        bird.getLives() == solutionLive
        bird.getHealth() == solutionHealth

        where:
        lives | solutionLive | solutionHealth
        1 | 4 | 100
        9 | 0 | 0
    }

    def"Decrease Health"(){
        when:
        bird.decreaseHealth(50)

        then:
        bird.getHealth() == 50
    }



    def"isDead - True(Lives == 0 && Health == 0)"(){
        given:
        bird.setLives(0)
        bird.setHealth(0)

        expect:
        bird.isDead() == true
    }

    def"isDead - False(Lives == 0 && Health != 0)"(){
        given:
        bird.setLives(0)
        bird.setHealth(50)

        expect:
        bird.isDead() == false
    }

    def"isDead - False"(){
        given:
        bird.setLives(3)
        bird.setLives(50)

        expect:
        bird.isDead() == false
    }

    def"Increase Speed"(){
        when:
        bird.increaseSpeed(2)
        then:
        bird.getSpeed() == 4
    }
}
