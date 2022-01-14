package Controls;

import Rectangle.Dimension;
import Rectangle.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Pipe extends Entities{

    public Pipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    abstract void draw(TextGraphics screen);//to be implemented later

    @Override
    public boolean update(int limit) {
        if (rectangle.getX() + rectangle.getWidth() < limit) {
            return false;
        }
        rectangle.updateX(speed);
        return true;
    }


}
