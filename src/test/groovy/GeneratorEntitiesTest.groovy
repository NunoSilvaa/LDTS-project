import flappyBird.entities.enemies.Enemy
import flappyBird.entities.pipes.Pipe
import flappyBird.entities.powerups.Powerup
import flappyBird.GeneratorEntities
import spock.lang.Specification

class generatorEntitiesTest extends Specification{
    private def generator
    void setup(){
        def powerUps = new ArrayList<String>()
        def enemies = new ArrayList<String>()
        powerUps.add("Faster")
        powerUps.add("Health")
        powerUps.add("Invincible")
        powerUps.add("Life")
        powerUps.add("Slower")
        enemies.add("Bee")
        enemies.add("DarthVader")
        enemies.add("Falcon")
        enemies.add("Wasp")
        generator = new GeneratorEntities(40,40, enemies, powerUps)
    }

    def"Generate Enemy"(){
        when:
        def enemy =  generator.generateRandomEnemy()

        then:
        enemy instanceof Enemy
        enemy.getPosition().getX() >= 0
        enemy.getPosition().getX() <= 40
        enemy.getPosition().getY() >= 0
        enemy.getPosition().getY() <= 40
    }

    def"Generate PowerUp"(){
        when:
        def powerUp = generator.generateRandomPowerUp()

        then:
        powerUp instanceof Powerup
        powerUp.getPosition().getX() >= 0
        powerUp.getPosition().getX() <= 40
        powerUp.getPosition().getY() >= 0
        powerUp.getPosition().getY() <= 40
    }

    def"Generate Pipes"(){
        when:
        def pipes = generator.generateRandomPipes()

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
