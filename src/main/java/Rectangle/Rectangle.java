package Rectangle;

public class Rectangle {
    private Position position;
    private Dimension dimension;

    public Rectangle(Position position, Dimension dimension){
        this.position = position;
        this.dimension = dimension;
    }

    public boolean intersect(Rectangle otherRectangle){
        Position l1 = new Position(getX(),getY());
        Position r1 = new Position(getX()+getWidth(),getY()+getHeight());
        Position l2 = new Position(otherRectangle.getX(), otherRectangle.getY());
        Position r2 = new Position(otherRectangle.getX() + otherRectangle.getWidth(), otherRectangle.getY()+otherRectangle.getHeight());

        if(l1.getY()>r2.getY() || r1.getY() < l2.getY() || l1.getX()>r2.getX() || r1.getX()<l2.getX())
            return false;
        return true;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public void setX(int x){position.setX(x);}

    public void setY(int y){position.setY(y);}

    public void updateX(int speed){position.updateX(speed);}

    public void updateY(int speed){position.updateY(speed);}

    public int getHeight(){return dimension.getHeight();}

    public int getWidth(){return dimension.getWidth();}

    public void setHeight(int height){dimension.setHeight(height);}

    public void setWidth(int width){dimension.setWidth(width);}
}