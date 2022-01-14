import spock.lang.Specification
import Rectangle.*

class RectangleTest extends Specification{
    private Rectangle rectangle

    void setup(){
        rectangle = new Rectangle(new Position(30,30), new Dimension(10,10))
    }

    def"Test Update Y"(){
        when:
            rectangle.updateY(10)
        then:
            rectangle.getY() == 40
    }

    def"Test Update  X"(){
        when:
            rectangle.updateX(10)
        then:
            rectangle.getX() == 20
    }

    def"Test intersect"(){
        expect:
        rectangle.intersect(new Rectangle(pos, dimension)) == bool


        where:
        pos | dimension | bool
        new Position(18,0) | new Dimension(1,1) |false
        new Position(40,0) | new Dimension(1,1) |false
        new Position(25,35) | new Dimension(1,1) |false
        new Position(43,35) | new Dimension(1,1) |false

        new Position(29,35) | new Dimension(1,1) |true
        new Position(40,35) | new Dimension(1,1) |true
        new Position(30,29) | new Dimension(1,1) |true
        new Position(32,40) | new Dimension(1,1) | true
    }

}
