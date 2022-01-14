package Controls;

import Rectangle.*;
import com.googlecode.lanterna.graphics.TextGraphics;


abstract class Entities {
    Rectangle rectangle;
    protected  int speed;

    public Entities(Position position, Dimension dimension, int speed){
        this.rectangle = new Rectangle(position, dimension);
        this.speed = speed;
    }

    public Rectangle getRectangle(){return rectangle;}

    public Position getPosition(){
        return rectangle.getPosition();
    }

    public void setPosition(Position position){rectangle.setPosition(position);}

    public Dimension getDimension(){
        return rectangle.getDimension();
    }

    public void setDimension(Dimension dimension){rectangle.setDimension(dimension);}

    public void increaseSpeed(int delta){this.speed += delta;}

    public boolean intersect(Entities entity){
        return rectangle.intersect(entity.getRectangle());
    }

    abstract boolean update(int limit);

    abstract void draw(TextGraphics screen);
}
