package flappyBird.entities.enemies;

public interface EnemyObserver {
    void energyChanged(Enemy enemy);
    void PositionChanged(Enemy enemy);
}
