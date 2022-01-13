import spock.lang.Specification
import Rectangle.*

class RectangleTest extends Specification{
    private Rectangle rectangle

    void setup(){
        rectangle = new Rectangle(new Position(20,0), new Dimension(10,10))
    }

    def"Test Update Y"(){
        when:
            rectangle.updateY(10)
        then:
            rectangle.getY() == 20
    }

    def"Test Update  X"(){
        when:
            rectangle.updateX(10)
        then:
            rectangle.getX() == 0
    }

    def"Test intersect(Top)"(){
        expect:
        rectangle.intersect(new RectangleTest(pos, dimension))

        where:
        pos | dimension | bool
        new Position(18,0) | new Dimension(1,1) |false
        new Position(31,0) | new Dimension(1,1) |false
        new Position(25,11) | new Dimension(1,1) |false
        new Position(19,0) | new Dimension(1,1) |true
        new Position(29,0) | new Dimension(1,1) |true
        new Position(19,10) | new Dimension(1,1) |true
        new Position(29,10) | new Dimension(1,1) |true
        new Position(25,5) | new Dimension(1,1) | true
    }
    def"Test intersect(Bottom)"(){
        given:
        Rectangle r1 = new Rectangle(new Position(20,30), new Dimension(10,10))
        expect:
        r1.intersect(new RectangleTest(pos, dimension))

        where:
        pos | dimension | bool
        new Position(19,0) | new Dimension(1,1) | false
        new Position(20,15) | new Dimension(1,1) |false
        new Position(31,39) | new Dimension(1,1) | false
        new Position(19,39) | new Dimension(1,1) |true
        new Position(30,39) | new Dimension(1,1) |true
        new Position(19,29) | new Dimension(1,1) |true
        new Position(30,29) | new Dimension(1,1) |true
        new Position(25,35) | new Dimension(1,1) |true
    }

}
