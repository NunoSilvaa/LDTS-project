package flappyBird;

import flappyBird.entities.Entities;
import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.powerups.Powerup;

import java.util.List;
import java.util.Random;

public class GeneratorEntities {
    private Random random;
    private int width;
    private int height;
    private List<String> powerUps;
    private List<String> enemies;

    public  GeneratorEntities(int width, int height, List<String> powerUps, List<String> enemies){
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.powerUps = powerUps;
        this.enemies = enemies;
    }

    public Enemy generateRandomEnemy(){

    }

    public Powerup generateRandomPowerUp(){}
    public List<Pipe> generateRandomPipes(){}

}
