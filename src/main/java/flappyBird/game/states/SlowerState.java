package flappyBird.game.states;

import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.pipes.Pipe;
import flappyBird.game.Arena;

public class SlowerState extends ArenaState {
    public SlowerState(Arena arena) {
        super(arena);
        for(Pipe pipe: arena.getPipes()){
            pipe.increaseSpeed(1);
        }
        for(Enemy enemy: arena.getEnemies()){
            enemy.increaseSpeed(1);
        }
        arena.getBird().increaseSpeed(1);
    }

    @Override
    public void addPipes(){
        arena.addPipes();
    };

    public void collideEntities(){
        arena.collideEntities();
    };
}