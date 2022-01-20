package flappyBird.entities.observer;

import flappyBird.entities.Entities;
import flappyBird.rectangle.Position;

public class AgainstBirdObserver implements EntitiesObserver{
    private int width;
    private int height;

    public AgainstBirdObserver(int width, int height){
        this.width = width;
        this.height = height;
    }


    @Override
    public void executeObserver(Entities entity) {
        entity.setPosition(new Position(-30,-30));
    }
}
