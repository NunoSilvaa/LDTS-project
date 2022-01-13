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

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        screen.fillRectangle(new TerminalPosition(position.getX(),position.getY()), new TerminalSize(dimension.getWidth(),dimension.getHeight()),  ' ');
    }

    @Override
    public boolean update(int limit) {
        if (position.getX() + dimension.getWidth() < limit) {
            return false;
        }
        position.updateX(speed);
        return true;
    }

    abstract boolean overlap(Position birdPos);

}
