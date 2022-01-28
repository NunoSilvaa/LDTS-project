package flappyBird;

import flappyBird.entities.enemies.*;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.powerups.*;
import flappyBird.game.Arena;
import flappyBird.game.states.FasterState;
import flappyBird.game.states.SlowerState;
import flappyBird.move.Diagonal;
import flappyBird.move.Horizontal;
import flappyBird.move.Vertical;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorEntities {
    private Random random;
    private int width;
    private int height;


    public  GeneratorEntities(int width, int height){
        this.width = width;
        this.height = height;
        this.random = new Random();

    }

    public Enemy generateRandomEnemy(Arena arena){
        int speed = 2;
        if(arena.getState() instanceof FasterState){speed = 3;}
        else if(arena.getState() instanceof SlowerState){speed = 1;}
        else{
            speed = 2;
        }
        return switch (random.nextInt(4)) {
            case 0 -> new Bee(new Position(width - 6, 0), new Dimension(1, 1),speed, new Diagonal());
            case 1 -> new DarthVader(new Position(width, 0), new Dimension(1, 1), speed, new Vertical());
            case 2 -> new Falcon(new Position(width - 6 , 0), new Dimension(1, 1), speed, new Diagonal());
            case 3 -> new Wasp(new Position(width, random.nextInt(height) + 1), new Dimension(1, 1), speed, new Horizontal());
            default -> null;
        };
    }

    public Powerup generateRandomPowerUp(Arena arena){
        int speed;
        if(arena.getState() instanceof FasterState){
            speed = 3;
        }
        else if(arena.getState() instanceof SlowerState){
            speed = 1;
        }
        else{
           speed = 2;
        }

        return switch (random.nextInt(4)){
            case 0 -> new Faster(new Position(width, random.nextInt(height)+1), new Dimension(1,1), speed, new Horizontal());
            case 1 -> new Health(new Position(width, random.nextInt(height)+1), new Dimension(1,1), speed, new Horizontal());
            case 2 -> new Life(new Position(width,random.nextInt(height)+1), new Dimension(1,1), speed, new Horizontal());
            case 3 -> new Slower(new Position(width, random.nextInt(height)+1), new Dimension(1,1), speed, new Horizontal());
            default -> null;
        };
    }
    public List<Pipe> generateRandomPipes(Arena arena){
        int speed;
        if(arena.getState() instanceof FasterState){
            speed = 3;
        }
        else if(arena.getState() instanceof SlowerState){
            speed = 1;
        }
        else{
            speed = 2;
        }
        List<Pipe> pipes = new ArrayList<Pipe>();
        int heightPipe = random.nextInt(height/2) + 7;
        Pipe bottomPipe = new Pipe(new Position(width,heightPipe+7),  new Dimension(height-heightPipe+7,7),speed, new Horizontal());
        Pipe topPipe = new Pipe(new Position(width,0),  new Dimension(heightPipe,7),speed, new Horizontal());
        pipes.add(bottomPipe);
        pipes.add(topPipe);
        return pipes;
    }

}
