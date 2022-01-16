package flappyBird.move;

import flappyBird.entities.Entities;
import flappyBird.entities.enemies.Enemy;
import flappyBird.rectangle.Position;

public class Vertical implements Move{
    @Override
    public void update(Entities entity) {
         entity.updateY();
    }
}
