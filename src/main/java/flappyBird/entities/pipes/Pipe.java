package flappyBird.entities.pipes;

import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.rectangle.Rectangle;

public abstract class Pipe extends Entities {

    public Pipe(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed, move);
    }

    public Pipe(Rectangle rectangle, int speed, Move move){
        super(rectangle, speed, move);
    }


    public void collideBird(Bird bird){
        if(this.intersect(bird))
            bird.decreaseLives(1);
    }

}
