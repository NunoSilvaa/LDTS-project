package flappyBird.entities.powerups;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.MusicPlayer;
import flappyBird.entities.Entities;
import flappyBird.game.Arena;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import flappyBird.rectangle.Rectangle;

public abstract class Powerup extends Entities {
    protected MusicPlayer collideBird;


    public Powerup(Position position, Dimension dimension, int speed , Move move){
        super(position, dimension, speed, move);
        collideBird = new MusicPlayer("collide.wav");
    }

    public Powerup(Rectangle rectangle, int speed , Move move){
        super(rectangle, speed, move);
    }

    @Override
    public abstract void draw(TextGraphics screen);

    public abstract void effect(Arena arena);

    @Override
    public void move(){
        move.update(this);
    }
}