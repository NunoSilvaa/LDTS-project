package flappyBird.entities.observer;

import flappyBird.entities.Entities;
import flappyBird.rectangle.Position;

public class AgainstBirdObserver implements EntitiesObserver{

    public AgainstBirdObserver(){
    }


    @Override
    public void executeObserver(Entities entity) {
        entity.setPosition(new Position(-30,-30));
    }
}
