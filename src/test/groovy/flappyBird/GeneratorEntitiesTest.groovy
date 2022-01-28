package flappyBird

import flappyBird.entities.enemies.Enemy
import flappyBird.entities.pipes.Pipe
import flappyBird.entities.powerups.Powerup
import flappyBird.GeneratorEntities
import flappyBird.game.Arena
import spock.lang.Specification

class GeneratorEntitiesTest extends Specification{
    private def generator
    private def arena
    void setup(){
        generator = new GeneratorEntities(40,40)
        arena = new Arena(40,40)
    }

    def"Generate Enemy"(){
        when:
        def enemy =  generator.generateRandomEnemy(arena)

        then:
        enemy instanceof Enemy
        enemy.getPosition().getX() >= 0
        enemy.getPosition().getX() <= 40
        enemy.getPosition().getY() >= 0
        enemy.getPosition().getY() <= 40
    }

    def"Generate PowerUp"(){
        when:
        def powerUp = generator.generateRandomPowerUp(arena)

        then:
        powerUp instanceof Powerup
        powerUp.getPosition().getX() >= 0
        powerUp.getPosition().getX() <= 40
        powerUp.getPosition().getY() >= 0
        powerUp.getPosition().getY() <= 40
    }

    def"Generate Pipes"(){
        when:
        def pipes = generator.generateRandomPipes(arena)

        then:
        pipes.size() == 2
        for(Pipe pipe: pipes){
            pipe.getPosition().getX() >= 0
            pipe.getPosition().getX() <= 40
            pipe.getPosition().getY() >= 0
            pipe.getPosition().getY() <= 40
        }
    }

}
