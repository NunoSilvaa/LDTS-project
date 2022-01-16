package flappyBird.entities;

import flappyBird.move.Move;
import flappyBird.rectangle.Dimension;
import flappyBird.rectangle.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Bird extends Entities{
    private int slapHeight;
    private int lives;
    private int health;


    public Bird(Position position, Dimension dimension, int speed, Move move, int slapHeight, int lives){
        super(position,dimension, speed, move);
        this.slapHeight = slapHeight;
        this.lives = lives;
        health = 100;
    }

    @Override
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#8B0000"));
        screen.fillRectangle(new TerminalPosition(rectangle.getX(),rectangle.getY()), new TerminalSize(rectangle.getWidth(), rectangle.getHeight()),  ' ');
    }

    public void slap(){
        rectangle.updateY(-slapHeight);
        for(EntitiesObserver observer: observers){
            observer.positionChanged(this);
        }
    }

    public boolean isDead(){
        return lives == 0 && health == 0;
    }

    public int getLives(){return lives;}

    public int getHealth(){return health;}

    public void decreaseHealth(int health){
        int finalHealth = this.health - health;
        if(finalHealth < 0) {
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
}