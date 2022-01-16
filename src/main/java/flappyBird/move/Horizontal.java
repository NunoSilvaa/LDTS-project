package flappyBird.move;

import flappyBird.entities.Entities;

public class Horizontal implements Move{
    @Override
    public void update(Entities entity) {
        entity.updateX();
    }
}
