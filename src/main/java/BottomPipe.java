public class BottomPipe extends Pipe{

    public BottomPipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public boolean overlap(Position birdPos, Dimension birdDimension){
        if(!(position.getX()+dimension.getWidth() < birdPos.getX() || birdPos.getX() + birdDimension.getWidth() < position.getX())){
            if(birdPos.getY() <= 40 && birdPos.getY() + birdDimension.getHeight() >= position.getY() ){
                return true;
            }
        }
        return false;
    }
}
