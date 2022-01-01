import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bird extends Entities{
    private int slapHeight;
    public Bird(Position position, Dimension dimension, int speed, int slapHeight){
        super(position,dimension, speed);
        this.slapHeight = slapHeight;
    }
    @Override
    public void update(){
        position.updateY(speed);
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#8B0000"));
        screen.fillRectangle(new TerminalPosition(position.getX(),position.getY()), new TerminalSize(dimension.getHeight(), dimension.getLength()),  ' ');
    }

    public void slap(int limit){
        if(position.getY()-slapHeight >= limit)
            position.updateY(-slapHeight);
        else
            position.setY(limit);
    }
}
