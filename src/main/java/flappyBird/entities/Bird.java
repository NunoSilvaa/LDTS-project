package flappyBird.entities;

import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Bird extends Entities{
    private int slapHeight;
    private boolean dead;


    public Bird(Position position, Dimension dimension, int speed, int slapHeight){
        super(position,dimension, speed);
        this.slapHeight = slapHeight;
        dead = false;
    }
    @Override
    public boolean update(int limit) {


        if (rectangle.getY() + speed > limit) {
            dead = true;
            return true;
        }

        if(rectangle.getY() < 0 ){
            rectangle.setY(0);
            return true;
        }

        rectangle.updateY(speed);
        return false;
    }


    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#8B0000"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(), rectangle.getHeight()),  ' ');
    }

    public void slap(int limit){
        if(rectangle.getY()-slapHeight >= limit)
            rectangle.updateY(-slapHeight);
        else
            rectangle.setY(limit);
    }

    public boolean isDead(){
        return dead;
    }
}