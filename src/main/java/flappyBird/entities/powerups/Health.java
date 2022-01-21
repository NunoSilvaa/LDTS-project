package flappyBird.entities.powerups;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.observer.EntitiesObserver;
import flappyBird.entities.powerups.Powerup;
import flappyBird.game.Arena;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import flappyBird.rectangle.Rectangle;

import java.util.ArrayList;

public class Health extends Powerup {

    public Health(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed,move);
    }

    public Health(Rectangle rectangle, int speed , Move move){
        super(rectangle, speed, move);
    }


    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#F30000"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(), rectangle.getY()), new TerminalSize(rectangle.getWidth(), rectangle.getHeight()),  'H');
    }

    @Override
    public void effect(Arena arena){
        if(arena.getBird().intersect(this)) {
            collideBird.playSound();
            arena.getBird().setHealth(100);
            for (EntitiesObserver observer : observers) {
                observer.executeObserver(this);
            }
        }
    }
}

