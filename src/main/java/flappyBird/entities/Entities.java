package flappyBird.entities;
import flappyBird.entities.enemies.Enemy;
import flappyBird.move.Move;
import flappyBird.rectangle.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;


public abstract class Entities {
    protected List<EntitiesObserver> observers;
    protected Move move;
    protected Rectangle rectangle;
    protected int speed;

    public Entities(Position position, Dimension dimension, int speed, Move move){
        this.rectangle = new Rectangle(position, dimension);
        this.speed = speed;
        this.move = move;
        observers = new ArrayList<>();
    }

    public void setMove(Move move){this.move = move;}

    public void addObserver(EntitiesObserver observer){
        observers.add(observer);
    }

    public Rectangle getRectangle(){return rectangle;}

    public void setRectangle(Rectangle rectangle){this.rectangle = rectangle;}

    public Position getPosition(){
        return rectangle.getPosition();
    }

    public Dimension getDimension(){
        return rectangle.getDimension();
    }

    public void setPosition(Position position){rectangle.setPosition(position);}

    public void setDimension(Dimension dimension){rectangle.setDimension(dimension);}

    public void setSpeed(int speed){this.speed = speed;}

    public void increaseSpeed(int delta){this.speed += delta;}

    public int getSpeed(){return speed;}

    public boolean intersect(Entities entity){
        return rectangle.intersect(entity.getRectangle());
    }

    public void updateX(){rectangle.updateX(speed);}

    public void updateY(){rectangle.updateY(speed);}

    public void move(){
        move.update(this);
        for(EntitiesObserver observer: observers){
            observer.positionChanged(this);
        }
    }

    public abstract void draw(TextGraphics screen);

}
