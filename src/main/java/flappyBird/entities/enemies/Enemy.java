package flappyBird.entities.enemies;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.entities.weapons.*;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Entities{
    private final Weapon weapon;

    public Enemy(Position position, Dimension dimension, int speed, Move move) {
        super(position, dimension, speed, move);
        this.weapon = createWeapon();
    }


    public Weapon getWeapon(){
        return weapon;
    }

    public void attack(Bird bird){
        if(this.intersect(bird)){
            weapon.attackBird(bird);
        }
    }

    protected abstract Weapon createWeapon();

}
