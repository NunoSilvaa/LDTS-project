package entitiesTest.weapons

import flappyBird.entities.Bird
import flappyBird.entities.weapons.Poison
import spock.lang.Specification

class PoisonTest extends Specification{
    def"Poison Test"(){
        given:
        def bird = Mock(Bird.class)
        Poison weapon = new Poison()

        when:
        weapon.attackBird(bird)

        then:
        1 * bird.decreaseLives(1)
        1* bird.decreaseHealth(50)
    }
}
