package flappyBird.entities;

import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Pipe extends Entities{

    public Pipe(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed, move);
    }

    public abstract void draw(TextGraphics screen);//to be implemented later

}
