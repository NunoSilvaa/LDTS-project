package flappyBird.entities.observer;

import flappyBird.MusicPlayer;
import flappyBird.entities.Bird;
import flappyBird.entities.Entities;

public class BirdObserver implements EntitiesObserver{
    private int height;
    private MusicPlayer collideSound;

    public BirdObserver(int height){
        this.height = height;
        collideSound = new MusicPlayer("collide.wav");
    }


    @Override
    public void executeObserver(Entities entity) {
        if (entity.getPosition().getY() < 0)
            entity.setY(0);
        else if (entity.getPosition().getY() + entity.getDimension().getHeight() >= height) {
            if(entity instanceof Bird){
                ((Bird) entity).decreaseLives(1);
            }
            collideSound.playSound();
            entity.setY(height/2);
        }
    }
}
