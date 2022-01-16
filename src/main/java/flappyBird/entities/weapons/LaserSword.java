package flappyBird.entities.weapons;

import flappyBird.entities.Bird;

public class LaserSword extends Weapon {
    @Override
    public void attackBird(Bird bird) {
        bird.decreaseLives(1);
    }
}
