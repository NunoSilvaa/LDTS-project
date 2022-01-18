package flappyBird.entities.weapons;

import flappyBird.entities.Bird;

public class Bite extends Weapon {
    @Override
    public void attackBird(Bird bird) {
        bird.decreaseHealth(50);
    }
}
