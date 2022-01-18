package flappyBird.entities.pipes;

import flappyBird.entities.pipes.Pipe;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HoverPipe extends Pipe{
    public HoverPipe(Position position, Dimension dimension, int speed, Move move){
        super(position, dimension, speed, move);
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(),rectangle.getHeight()),  ' ');
    }
}
