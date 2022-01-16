package flappyBird.entities.enemies;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.weapons.Weapon;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public class Bee extends Enemy{

    public Bee(Position position, Dimension Dimension, int speed){

    }


    @Override
    protected Weapon createWeapon() {
        return null;
    }

    @Override
    public boolean update(int limit) {
        return false;
    }

    @Override
    public void draw(TextGraphics screen) {

    }

}
