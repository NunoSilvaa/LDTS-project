package flappyBird.entities.powerups;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.game.Arena;
import flappyBird.game.states.InvincibleState;
import flappyBird.game.states.SlowerState;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public class Invincible extends Powerup{
    public Invincible(Position position, Dimension dimension, int speed, Move move){
        super(position,dimension, speed,move);
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#F30000"));
        screen.putString(rectangle.getX(), rectangle.getY(), "]^");
    }

    @Override
    public void effect(Arena arena){
        arena.setState(new InvincibleState(arena));
    }
}
