package flappyBird.entities.powerups;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.observer.EntitiesObserver;
import flappyBird.game.Arena;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import flappyBird.rectangle.Rectangle;

public class Life extends Powerup {

    public Life(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed,move);
    }

    public Life(Rectangle rectangle, int speed , Move move){
        super(rectangle, speed, move);
    }


    @Override
    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#F30000"));
        screen.putString(rectangle.getX(), rectangle.getY(), "#$");
    }

    @Override
    public void effect(Arena arena){
        if(arena.getBird().intersect(this)) {
            arena.getBird().increaseLives(1);
            for(EntitiesObserver observer: observers){
                observer.executeObserver(this);
            }
        }
    }
}

