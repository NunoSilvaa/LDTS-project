package flappyBird.entities.weapons;

import flappyBird.entities.Bird;

public class Poison extends Weapon {
    @Override
    public void attackBird(Bird bird) {
        bird.decreaseLives(1);
        bird.decreaseHealth(50);
    }
}
