import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Entities {
    protected  Position position;
    protected  Dimension dimension;
    protected  int speed;

    public Entities(Position position, Dimension dimension, int speed){
        this.position = position;
        this.dimension = dimension;
        this.speed = speed;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){this.position = position;}

    public Dimension getDimension(){
        return dimension;
    }

    public void increaseSpeed(int delta){this.speed += delta;}

    abstract bool update();

    abstract void draw(TextGraphics screen);
}
