package flappyBird.entities.powerups;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.powerups.Powerup;
import flappyBird.game.Arena;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

import java.util.ArrayList;

public class Life extends Powerup {

    public Life(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed,move);
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#F30000"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(), rectangle.getY()), new TerminalSize(rectangle.getWidth(), rectangle.getHeight()),  'L');
    }

    @Override
    public void effect(Arena arena){
        arena.getBird().increaseLives(1);
    }
}

