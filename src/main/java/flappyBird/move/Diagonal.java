package flappyBird.move;

import flappyBird.entities.Entities;
import flappyBird.rectangle.Position;

public class Diagonal implements Move{
    @Override
    public void update(Entities entity) {
        entity.updateX();
        entity.updateY();
    }
}
