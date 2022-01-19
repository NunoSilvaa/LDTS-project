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
import flappyBird.rectangle.Rectangle;

public class Wasp extends Enemy{

    public Wasp(Position position, Dimension dimension, int speed, Move move) {
        super(position, dimension, speed, move);
    }

    public Wasp(Rectangle rectangle, int speed, Move move) {
        super(rectangle, speed, move);
    }

    @Override
    protected Weapon createWeapon() {
        return new Poison();
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#C6EA0F"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(), rectangle.getHeight()),  ' ');
    }
}
