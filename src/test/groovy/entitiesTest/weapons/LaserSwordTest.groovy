package entitiesTest.weapons

import flappyBird.entities.Bird
import flappyBird.entities.weapons.LaserSword
import spock.lang.Specification

class LaserSwordTest extends Specification{
    def"Laser Sword Test"(){
        given:
        def bird = Mock(Bird.class)
        LaserSword weapon = new LaserSword()

        when:
        weapon.attackBird(bird)

        then:
        1 * bird.decreaseLives(1)
    }
}
