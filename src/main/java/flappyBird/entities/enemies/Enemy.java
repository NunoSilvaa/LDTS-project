package flappyBird.entities.enemies;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.entities.weapons.*;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public abstract class Enemy extends Entities{
    private final Weapon weapon;
    private int energy;

    public Enemy(Position position, Dimension dimension, int speed) {}

    public Weapon getWeapon(){}

    public void attack(Bird bird){}

    protected abstract Weapon createWeapon();

    abstract boolean update(int limit);

    abstract void draw(TextGraphics screen);
}
