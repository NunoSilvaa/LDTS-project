package entitiesTest.weapons

import flappyBird.entities.Bird
import flappyBird.entities.weapons.Bite
import spock.lang.Specification

class BiteTest extends Specification{
    def"Bite Test"(){
        given:
        def bird = Mock(Bird.class)
        Bite weapon = new Bite()

        when:
        weapon.attackBird(bird)

        then:
        1 * bird.decreaseHealth(50)
    }
}
