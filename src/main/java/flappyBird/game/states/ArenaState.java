package flappyBird.game.states;

import flappyBird.game.Arena;

public abstract class ArenaState {
    protected final Arena arena;

    public ArenaState(Arena arena) {
        this.arena = arena;
    }

    public abstract void addPipes();


    public abstract void collideEntities();
}

