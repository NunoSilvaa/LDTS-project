package flappyBird.entities;

public interface EntitiesObserver {
    void positionChanged(Entities entity);
    void collideBird(Entities entity);
}
