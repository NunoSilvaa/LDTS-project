public class TopPipe extends Pipe{
    public TopPipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public boolean overlap(Position birdPos, Dimension birdDimension){
        if(!(position.getX() + dimension.getHeight() < birdPos.getX() || birdPos.getX()+birdDimension.getWidth() < position.getX())){
            if(birdPos.getY() <= dimension.getHeight())
                return true;
        }
        return false;
    }
}
