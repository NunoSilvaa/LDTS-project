package flappyBird.entities.enemies;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.weapons.Poison;
import flappyBird.entities.weapons.Weapon;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public class Wasp extends Enemy{

    public Wasp(Position position, Dimension dimension, int speed, Move move) {
        super(position, dimension, speed, move);
    }

    @Override
    protected Weapon createWeapon() {
        return new Poison();
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#C6EA0F"));
        screen.putString(rectangle.getX(), rectangle.getY(), ")*");
    }
}
