package flappyBird.entities.enemies;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.weapons.LaserSword;
import flappyBird.entities.weapons.Weapon;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import flappyBird.rectangle.Rectangle;

public class DarthVader extends Enemy {

    public DarthVader(Position position, Dimension dimension, int speed, Move move) {
        super(position, dimension, speed, move);
    }

    public DarthVader(Rectangle rectangle, int speed, Move move) {
        super(rectangle, speed, move);
    }

    @Override
    protected Weapon createWeapon() {
        return new LaserSword();
    }


    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#030300"));
        screen.putString(rectangle.getX(), rectangle.getY(), "'(");
    }
}