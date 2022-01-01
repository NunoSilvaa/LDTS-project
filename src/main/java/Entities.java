import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Entities {
    protected  Position position;
    protected final Dimension dimension;

    public Entities(Position position, Dimension dimension){
        this.position = position;
        this.dimension = dimension;
    }

    abstract void update();
    abstract void render();

}
