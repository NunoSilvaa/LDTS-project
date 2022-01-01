import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pipe extends Entities{
    public Pipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        screen.fillRectangle(new TerminalPosition(position.getX(),position.getY()), new TerminalSize(dimension.getHeight(), dimension.getLength()),  ' ');
    }

    @Override
    public void update(){
        position.updateX(speed);
    }
}
