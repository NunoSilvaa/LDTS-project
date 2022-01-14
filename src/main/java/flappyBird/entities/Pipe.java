package flappyBird.entities;

import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Pipe extends Entities{

    public Pipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    public abstract void draw(TextGraphics screen);//to be implemented later

    @Override
    public boolean update(int limit) {
        if (rectangle.getX() + rectangle.getWidth() < limit) {
            return false;
        }
        rectangle.updateX(speed);
        return true;
    }


}
