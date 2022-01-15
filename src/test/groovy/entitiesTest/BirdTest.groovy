package groovy.entitiesTest

import flappyBird.entities.Bird
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class BirdTest extends Specification{
    private Position position
    private Dimension dimension
    private Bird bird
    private TextGraphics screen;
    private boolean result;

    def setup(){
        position = new Position(20,20)
        dimension = new Dimension(10,10)
        bird = new Bird(position, dimension, 2, 3, 5)
        screen = Mock(TextGraphics)
    }

    def"Gravity test"(){
        when:
        result = bird.update(30);

        then:
        bird.getPosition() == new Position(20, 22)
        result == false
    }

    def"Gravity test limits"(){
        when:
        for(int i = 0; i < 11; i++)
            result = bird.update(30);

        then:
        bird.getPosition() == new Position(20, 30)
        result == true
    }

    def"Slap test limits"(){
        when:
        for(int i = 0; i < 11; i++)
            bird.slap(0)

        then:
        bird.getPosition() == new Position(20, 0)
    }

    def"Slap test"(){
        when:
        bird.slap(0)

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

    def"Decrease Health - 2"(){
        given:
        def specialBird = Mock(Bird.class)
        when:
        specialBird.decreaseHealth(120)
        then:
        1 * specialBird.decreaseLives(_)
    }
}
