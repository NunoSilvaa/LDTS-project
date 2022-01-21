package flappyBird.game;

import flappyBird.GeneratorEntities;
import flappyBird.entities.*;
import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.observer.AgainstBirdObserver;
import flappyBird.entities.observer.BirdObserver;
import flappyBird.entities.observer.EntitiesObserver;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.powerups.Faster;
import flappyBird.entities.powerups.Powerup;
import flappyBird.game.states.ArenaState;
import flappyBird.game.states.NormalState;
import flappyBird.move.Diagonal;
import flappyBird.move.Vertical;
import flappyBird.rectangle.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.*;

public class Arena {
    private static Bird singleton = null;
    private final int width;
    private final int height;

    private final  Bird bird;
    private List<Pipe> pipes;
    private List<Enemy> enemies;
    private List<Powerup> powerUps;

    private EntitiesObserver againstBird;
    private EntitiesObserver birdObserver;
    private final GeneratorEntities generatorEntities;
    private ArenaState state;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        generatorEntities = new GeneratorEntities(width, height);
        againstBird = new AgainstBirdObserver(width, height);
        birdObserver = new BirdObserver(height);
        bird = getInstance();
        addBird();
        enemies = new ArrayList<Enemy>();
        powerUps = new ArrayList<Powerup>();
        pipes = new ArrayList<Pipe>();
        addPipes();
        state = new NormalState(this);
    }

    public Arena(int width, int height, Bird bird){//For Mocks
        this.height = height;
        this.width = width;
        againstBird = new AgainstBirdObserver(width, height);
        birdObserver = new BirdObserver(height);

        this.bird = bird;
        addBird();
        enemies = new ArrayList<Enemy>();
        powerUps = new ArrayList<Powerup>();
        pipes = new ArrayList<Pipe>();
        state = new NormalState(this);
        generatorEntities = new GeneratorEntities(width, height);

    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public Bird getInstance(){
        if(singleton == null)
            singleton = new Bird(new Position(width/2,height/2),new Dimension(1,1), 1,new Vertical(),3,3);
        return singleton;
    }

    public void addBird(){
        bird.addObserver(birdObserver);
    }

    public void addPipes() {
        List<Pipe> tempPipeList = generatorEntities.generateRandomPipes();
        Pipe tempPipe1 = tempPipeList.get(0);
        Pipe tempPipe2 = tempPipeList.get(1);
        tempPipe1.addObserver(againstBird);
        tempPipe2.addObserver(againstBird);
        pipes.add(tempPipe1);
        pipes.add(tempPipe2);
    }

    public void addEnemies(){
        Enemy enemy = generatorEntities.generateRandomEnemy();
        enemy.addObserver(againstBird);
        enemies.add(enemy);
    }

    public void addPowerUp(){
        Powerup powerup = generatorEntities.generateRandomPowerUp();
        powerup.addObserver(againstBird);
        powerUps.add(powerup);
    }

    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        for(Pipe pipe : pipes)
            pipe.draw(screen);
        bird.draw(screen);
        for(Enemy enemy: enemies){
            enemy.draw(screen);
        }
        for(Powerup powerUp :powerUps){
            powerUp.draw(screen);
        }
    }

    public void collideEntities(){
        for(Pipe pipe: pipes)
            pipe.collideBird(bird);
        for(Enemy enemy: enemies)
            enemy.attack(bird);
        for (Powerup powerup: powerUps)
            powerup.effect(this);

    }

    public boolean update(boolean slapBird){
        if(slapBird){
            bird.slap();
        }else{
            bird.move();
        }

        if(bird.isDead()){
            return false;
        }

        for(Pipe pipe: pipes)
            pipe.move();
        for(Enemy enemy: enemies)
            enemy.move();
        for(Powerup powerup: powerUps)
            powerup.move();

        return true;
    }

    public boolean gameOver(){
        return bird.isDead();
    }

    //-----------

    public int getNumberPipes(){
        return pipes.size();
    }

    public void injectPipe(Pipe pipe){
        pipe.addObserver(againstBird);
        pipes.add(pipe);
    }

    public void injectEnemy(Enemy enemy){enemies.add(enemy);}

    public void injectPowerUps(Powerup powerup){powerUps.add(powerup);}

    public Bird getBird() {
        return this.bird;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public List<Powerup> getPowerups() {
        return powerUps;
    }

    public ArenaState getState(){return state;}

    public EntitiesObserver getEntities(){return againstBird;};
}
