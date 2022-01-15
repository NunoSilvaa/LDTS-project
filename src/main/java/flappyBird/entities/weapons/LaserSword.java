package flappyBird.entities.weapons;

import flappyBird.entities.Bird;

public class LaserSword extends Weapon {
    @Override
    void attackBird(Bird bird) {
        bird.decreaseLives(1);
    }
}
