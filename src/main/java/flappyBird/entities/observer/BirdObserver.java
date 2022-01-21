package flappyBird.entities.observer;

import flappyBird.entities.Bird;
import flappyBird.entities.Entities;
import flappyBird.rectangle.Position;

public class BirdObserver implements EntitiesObserver{
    private int width;
    private int height;

    public BirdObserver(int width, int height){
        this.width = width;
        this.height = height;
    }


    @Override
    public void executeObserver(Entities entity) {
        if (entity.getPosition().getY() < 0)
            entity.setY(0);
        else if (entity.getPosition().getY() + entity.getDimension().getHeight() >= height) {
            if(entity instanceof Bird){
                ((Bird) entity).decreaseLives(1);
            }
            entity.setY(height/2);
        }
    }
}
