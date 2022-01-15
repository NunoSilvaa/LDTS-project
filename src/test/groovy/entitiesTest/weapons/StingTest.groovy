package entitiesTest.weapons

import flappyBird.entities.Bird
import flappyBird.entities.weapons.Sting
import spock.lang.Specification

class StingTest extends Specification{
    def"Laser Sword Test"(){
        given:
        def bird = Mock(Bird.class)
        Sting weapon = new Sting()

        when:
        weapon.attackBird(bird)

        then:
        1 * bird.decreaseHealth(25)
    }
}
