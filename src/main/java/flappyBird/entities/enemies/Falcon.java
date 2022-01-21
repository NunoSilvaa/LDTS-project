package flappyBird.entities.enemies;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.Bird;
import flappyBird.entities.weapons.Bite;
import flappyBird.entities.weapons.Weapon;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public class Falcon extends Enemy{

    public Falcon(Position position, Dimension dimension, int speed, Move move) {
        super(position, dimension, speed, move);
    }

    @Override
    protected Weapon createWeapon() {
        return new Bite();
    }


    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#D8B116"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(), rectangle.getHeight()),  ' ');
    }
}
