package Controls;

import Rectangle.Dimension;
import Rectangle.Position;

public class BottomPipe extends Pipe{

    public BottomPipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public boolean overlap(Position birdPos){
        if(birdPos.getX() >= position.getX() && birdPos.getX() <= position.getX() + dimension.getWidth()){
            if(birdPos.getY() <= 40 && birdPos.getY() >= position.getY() ){
                return true;
            }
        }
        return false;
    }
}
