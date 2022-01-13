package Controls;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

abstract class PowerUp extends Entities{

    public PowerUp(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    abstract void draw(TextGraphics screen);

    @Override
    public boolean update(int limit) {
        return true;
    }

    abstract boolean effect(ArrayList<Pipe> pipes, Bird bird);

    public boolean overlap(Position birdPos){
        return false;
    };

}