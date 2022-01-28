package flappyBird.entities.weapons

import flappyBird.entities.Bird
import spock.lang.Specification

class StingTest extends Specification{
    def"Sting Test"(){
        given:
        def bird = Mock(Bird.class)
        Sting weapon = new Sting()

        when:
        weapon.attackBird(bird)

        then:
        1 * bird.decreaseHealth(25)
    }
}
