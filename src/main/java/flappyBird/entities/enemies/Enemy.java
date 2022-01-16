package flappyBird.entities.enemies;

import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.entities.weapons.*;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Entities{
    private final Weapon weapon;
    private final List<EnemyObserver> observers;
    private int energy;

    public Enemy(Position position, Dimension dimension, int speed) {
        super(position, dimension, speed);
        this.weapon = createWeapon();
        this.energy = 1;
        this.observers = new ArrayList<>();
    }

    public void addEnemyObserver(EnemyObserver observer){
        this.observers.add(observer);
    }

    public int getEnergy(){
        return energy;
    }

    public void setEnergy(int energy){
        this.energy = energy;
        for(EnemyObserver observer: observers)
            observer.energyChanged(this);
    }

    public

    public Weapon getWeapon(){
        return weapon;
    }

    public void attack(Bird bird){
        if(this.intersect(bird)){
            weapon.attackBird(bird);
        }
    }

    protected abstract Weapon createWeapon();

    abstract Position update();

    abstract void draw(TextGraphics screen);
}
