package flappyBird.entities.enemies;

import flappyBird.MusicPlayer;
import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.entities.observer.EntitiesObserver;
import flappyBird.entities.weapons.*;
import flappyBird.move.Move;
import flappyBird.rectangle.*;

public abstract class Enemy extends Entities{
    private MusicPlayer collideBird;
    private Weapon weapon;

    public Enemy(Position position, Dimension dimension, int speed, Move move) {
        super(position, dimension, speed, move);
        this.weapon = createWeapon();
        collideBird = new MusicPlayer("collide.wav");
    }

    public Enemy(Rectangle rectangle, int speed, Move move) {
        super(rectangle, speed, move);
        this.weapon = createWeapon();
        collideBird = new MusicPlayer("collide.wav");
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void setWeapon(Weapon weapon){this.weapon = weapon;}

    public void attack(Bird bird){

        if(this.intersect(bird)){
            collideBird.playSound();
            weapon.attackBird(bird);
            for(EntitiesObserver observer: observers){
                observer.executeObserver(this);
            }
        }
    }

    protected abstract Weapon createWeapon();

    @Override
    public void move(){
        move.update(this);
    }

}
