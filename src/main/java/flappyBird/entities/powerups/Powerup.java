package flappyBird.entities.powerups;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.Entities;
import flappyBird.game.Arena;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public abstract class Powerup extends Entities {

    public Powerup(Position position, Dimension dimension, int speed , Move move){
        super(position, dimension, speed, move);
    }

    public abstract void draw(TextGraphics screen);

    public abstract void effect(Arena arena);
}