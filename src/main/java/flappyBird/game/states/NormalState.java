package flappyBird.game.states;

import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.powerups.Powerup;
import flappyBird.game.Arena;

public class NormalState extends ArenaState {

    public NormalState(Arena arena) {
        super(arena);
        System.out.println("NormalState");
        for(Pipe pipe: arena.getPipes()){
            pipe.setSpeed(2);
        }
        for(Enemy enemy: arena.getEnemies()){
            enemy.setSpeed(2);
        }
        for(Powerup powerUp: arena.getPowerups()){
            powerUp.setSpeed(2);
        }

    }

    @Override
    public void addPipes(){
        arena.addPipes();
    };

    @Override
    public void collideEntities(){
        arena.collideEntities();
    };
}