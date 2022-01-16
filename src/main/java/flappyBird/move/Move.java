package flappyBird.move;

import flappyBird.entities.Entities;
import flappyBird.entities.enemies.Enemy;
import flappyBird.rectangle.Position;

public interface Move {
    Position update(Entities entity);
}
