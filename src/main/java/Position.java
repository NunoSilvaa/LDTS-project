import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private int speed;

    public Position(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed(){return speed;}

    public void increaseSpeed(int delta){this.speed += delta;}

    public void increaseX(int delta){this.x += delta;}

    public void increaseY(int delta){this.y += delta;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
