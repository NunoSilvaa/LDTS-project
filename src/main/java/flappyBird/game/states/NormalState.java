package flappyBird.game.states;

import flappyBird.game.Arena;

public class NormalState extends ArenaState {
    public NormalState(Arena arena) {
        super(arena);
    }

    @Override
    public void addPipes(){
        arena.addPipes();
    };

    public void collideEntities(){
        arena.collideEntities();
    };
}