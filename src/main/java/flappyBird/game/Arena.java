package flappyBird.game;

import flappyBird.entities.*;
import flappyBird.entities.enemies.DarthVader;
import flappyBird.entities.enemies.Enemy;
import flappyBird.entities.pipes.BottomPipe;
import flappyBird.entities.pipes.Pipe;
import flappyBird.entities.pipes.TopPipe;
import flappyBird.entities.powerups.Powerup;
import flappyBird.game.states.ArenaState;
import flappyBird.game.states.FasterState;
import flappyBird.game.states.NormalState;
import flappyBird.move.Diagonal;
import flappyBird.move.Horizontal;
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
    private ArenaState state;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        bird = getInstance();
        addBird();
        enemies = new ArrayList<Enemy>();
        powerUps = new ArrayList<Powerup>();
        pipes = new ArrayList<Pipe>();
        addPipes();
        state = new NormalState(this);
    }

    public Arena(int width, int height, Bird bird){
        this.height = height;
        this.width = width;
        this.bird = bird;
        enemies = new ArrayList<Enemy>();
        pipes = new ArrayList<Pipe>();
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public Bird getInstance(){
        if(singleton == null)
            singleton = new Bird(new Position(width/2,height/2),new Dimension(1,1), 1,new Vertical(),1,3);
        return singleton;
    }

    public void addBird(){
        bird.addObserver(new EntitiesObserver() {
            @Override
            public void positionChanged(Entities entity) {
                if (entity.getPosition().getY() < 0)
                    entity.setPosition(new Position(width / 2, 0));
                else if (entity.getPosition().getY() >= height) {
                    bird.decreaseLives(1);
                    entity.setPosition(new Position(width / 2, height / 2));
                    pipes.clear();
                }
            }

            @Override
            public void collideBird(Entities entity) {}
        });
    }

    public void addPipes() {
        Random random = new Random();
        int heightPipe = random.nextInt(height/2) + 7;
        BottomPipe bottomPipe = new BottomPipe(new Position(width,heightPipe+7),  new Dimension(height-heightPipe+7,7),1, new Horizontal());
        TopPipe topPipe = new TopPipe(new Position(width,0),  new Dimension(heightPipe,7),1, new Horizontal());
        pipes.add(bottomPipe);
        pipes.add(topPipe);

        EntitiesObserver pipeObserver = new EntitiesObserver() {
            @Override
            public void positionChanged(Entities entity){
                if(entity.getPosition().getX() + entity.getDimension().getWidth() < 0){
                    pipes.remove(entity);
                }
            }
            @Override
            public void collideBird(Entities entity){
                pipes.clear();
            }
        };

        bottomPipe.addObserver(pipeObserver);
        topPipe.addObserver(pipeObserver);
    }

    public void addEnemies(){
    }

    public void addPowerUp(Powerup powerup){
        powerUps.add(powerup);

        powerup.addObserver(new EntitiesObserver() {
            @Override
            public void positionChanged(Entities entity) {
                if(entity.getPosition().getX() + entity.getDimension().getWidth() < 0 || entity.getPosition().getY() > height || entity.getPosition().getY() + entity.getDimension().getHeight() < 0)
                    powerUps.remove(entity);
            }

            @Override
            public void collideBird(Entities entity) {
                    powerUps.remove(entity);
            }
        });
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

    }

    public void collideEntities(){
        for(Pipe pipe: pipes)
            pipe.collideBird(bird);
        for(Enemy enemy: enemies)
            enemy.attack(bird);
    }

    public void update(boolean slapBird){
        if(slapBird){
            bird.slap();
        }else{
            bird.move();
        }

        for(Pipe pipe: pipes)
            pipe.move();
        for(Enemy enemy: enemies)
            enemy.move();
        for(Powerup powerup: powerUps)
            powerup.move();
    }

    public boolean gameOver(){
        return bird.isDead();
    }


    //-----------

    public int getNumberPipes(){
        return pipes.size();
    }

    public void injectPipe(Pipe pipe){
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
}
