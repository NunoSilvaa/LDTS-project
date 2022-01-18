package flappyBird.game.states;

import flappyBird.game.Arena;

public class InvincibleState extends ArenaState {
    public InvincibleState(Arena arena) {
        super(arena);
    }

    @Override
    public void addPipes(){
        arena.addPipes();
    };

    //when our hero is invincible the collisions should not affect him and he should hover just like a ghost going through pipes and enemy attacks
    public void collideEntities(){
    };
}