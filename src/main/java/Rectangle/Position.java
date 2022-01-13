package Rectangle;


public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){this.x = x;}

    public void setY(int y){this.y = y;}

    public void increaseY(int delta){this.y += delta;}

    public void updateX(int speed){this.x -= speed;}

    public void updateY(int speed){this.y += speed;}


}
