package flappyBird;

import flappyBird.entities.Entities;
import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.powerups.Powerup;

import java.util.List;

public class GeneratorEntities {
    private int width;
    private int height;

    public  GeneratorEntities(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Enemy generateRandomEnemy(){}
    public Powerup generateRandomPowerUp(){}
    public List<Pipe> generateRandomPipes(){}

}
