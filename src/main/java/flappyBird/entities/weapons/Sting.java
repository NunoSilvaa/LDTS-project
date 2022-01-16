package flappyBird.entities.weapons;

import flappyBird.entities.Bird;

public class Sting extends Weapon {

    @Override
    public void attackBird(Bird bird) {
        bird.decreaseHealth(25);
    }
}
