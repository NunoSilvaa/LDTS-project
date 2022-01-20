package flappyBird.entities.pipes;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.entities.EntitiesObserver;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.rectangle.Rectangle;

public class Pipe extends Entities {

    public Pipe(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed, move);
    }

    public Pipe(Rectangle rectangle, int speed, Move move){
        super(rectangle, speed, move);
    }


    public void collideBird(Bird bird){
        if(this.intersect(bird)){
            bird.decreaseLives(1);
            for(EntitiesObserver observer: observers){
                observer.collideBird(this);
            }
        }
    }

    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(),rectangle.getHeight()),  ' ');
    }

}
