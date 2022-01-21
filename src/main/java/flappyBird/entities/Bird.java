package flappyBird.entities;

import com.googlecode.lanterna.TextCharacter;
import flappyBird.MusicPlayer;
import flappyBird.entities.observer.EntitiesObserver;
import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import flappyBird.rectangle.Rectangle;


public class Bird extends Entities{
    private int slapHeight;
    private int lives;
    private int health;
    private MusicPlayer slapSound;


    public Bird(Position position, Dimension dimension, int speed, Move move, int slapHeight, int lives) {
        super(position, dimension, speed, move);
        this.slapHeight = slapHeight;
        this.lives = lives;
        health = 100;
        slapSound = new MusicPlayer("slap.wav");
    }

    public Bird(Rectangle rectangle, int speed, Move move, int slapHeight, int lives) {
        super(rectangle, speed, move);
        this.slapHeight = slapHeight;
        this.lives = lives;
        health = 100;
    }


    @Override
    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#f4cf0d"));
        screen.putString(rectangle.getX(), rectangle.getY(), "%&");
        for(int i=0; i < lives;i++){
            screen.setForegroundColor(TextColor.Factory.fromString("#8B0000"));
            screen.putString(new TerminalPosition(1+(2*i),1),"#$");
        }
        screen.setForegroundColor(TextColor.Factory.fromString("#2B0000"));
        screen.fillRectangle(new TerminalPosition(40,1), new TerminalSize(health/10,1),  ' ');

    }

    public void slap(){
        rectangle.updateY(-slapHeight);
        slapSound.playSound();
        for(EntitiesObserver observer: observers){
            observer.executeObserver(this);
        }
    }

    public boolean isDead(){
        return lives == 0 && health == 0;
    }

    public int getLives(){return lives;}

    public int getHealth(){return health;}

    public void decreaseHealth(int health){
        int finalHealth = this.health - health;
        if(finalHealth <= 0) {
            this.decreaseLives(1);
        }
        else{
            this.health = finalHealth;
        }
    }

    public void decreaseLives(int numOfLives){
        if(this.lives - numOfLives < 0){
            this.lives = 0;
            this.health = 0;
        }
        else{
            this.lives -= numOfLives;
            this.health = 100;
        }


    }

    public void increaseLives(int numOfLives){
        this.lives += numOfLives;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setLives(int lives){this.lives = lives;}

    @Override
    public void move(){
        move.update(this);
        for(EntitiesObserver observer: observers)
            observer.executeObserver(this);
    }
}