public class TopPipe extends Pipe{
    public TopPipe(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public boolean overlap(Position birdPos){
        if(birdPos.getX() >= position.getX() && birdPos.getX() <= position.getX() + dimension.getWidth()){
            if(birdPos.getY() >= 0 && birdPos.getY() <= dimension.getHeight() ){
                return true;
            }
        }
        return false;
    }

}
