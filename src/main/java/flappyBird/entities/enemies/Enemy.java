package flappyBird.entities.enemies;

import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.entities.weapons.*;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

public abstract class Enemy extends Entities{
    private final Weapon weapon;
    private int energy;

    public Enemy(Position position, Dimension dimension, int speed) {
        this.weapon = createWeapon();
    }

    protected abstract Weapon createWeapon();

    public void attack(Bird bird){}

    abstract void update();

    abstract void draw();
}
