package flappyBird.game.states;

import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.powerups.Powerup;
import flappyBird.game.Arena;
import flappyBird.move.Horizontal;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import flappyBird.rectangle.Rectangle;

import java.util.List;
import java.util.Random;

public class FasterState extends ArenaState {
    public FasterState(Arena arena) {
        super(arena);
        System.out.print("FasterState");
        for(Pipe pipe: arena.getPipes()){
            pipe.setSpeed(3);
        }
        for(Enemy enemy: arena.getEnemies()){
            enemy.setSpeed(3);
        }
        for(Powerup powerUp: arena.getPowerups()){
            powerUp.setSpeed(3);
        }
    }

    @Override
    public void addPipes(){
        Random random = new Random();
        int heightPipe = random.nextInt(15) + 7;
        Pipe bottomPipe = new Pipe(new Position(60,heightPipe+7),  new Dimension(30-heightPipe+7,7),3, new Horizontal());
        Pipe topPipe = new Pipe(new Position(60,0),  new Dimension(heightPipe,7),3, new Horizontal());
        bottomPipe.addObserver(arena.getEntities());
        topPipe.addObserver(arena.getEntities());
        arena.getPipes().add(bottomPipe);
        arena.getPipes().add(topPipe);
    };

    @Override
    public void collideEntities(){
        arena.collideEntities();
    };
}