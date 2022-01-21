package flappyBird.move;

import flappyBird.entities.Entities;

public class Vertical implements Move{
    public Vertical(){}
    @Override
    public void update(Entities entity) {
         entity.updateY();
    }
}
