package flappyBird.entities.enemies;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.weapons.Weapon;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public class Wasp extends Enemy{

    public Wasp(Position position, Dimension dimension, int speed) {
    }

    @Override
    protected Weapon createWeapon() {
        return null;
    }

    @Override
    boolean update(int limit) {
        return false;
    }

    @Override
    void draw(TextGraphics screen) {

    }
}
