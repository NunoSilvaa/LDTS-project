package Controls;

import Rectangle.Dimension;
import Rectangle.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BottomPipe extends Pipe{

    public BottomPipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(),rectangle.getHeight()),  ' ');
    }

}
