package Controls;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Faster extends PowerUp{

    public Faster(Position position, Dimension dimension, int speed){
        super(position,dimension, speed);
    }

    @Override
    public boolean effect(ArrayList<Pipe> pipes, Bird bird){

        return false;
    }

    @Override
    public void draw(TextGraphics screen){

    }
}
