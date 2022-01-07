import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PipeTest extends Specification{
    private def topPipe
    private def bottomPipe
    private def screen;

    def setup(){
        topPipe = new topPipe(new Position(20,0), new Dimension(10,10), 2)
        bottomPipe = new bottomPipe(new Position(20,30), new Dimension(10,10), 2)
        screen = Mock(TextGraphics)
    }

    def"Update test (Bottom Pipe)"(){
        when:
            boolean result = bottomPipe.update(0)

        then:
            bottomPipe.getPosition() == new Position(18, 20)
            result == false
    }

    def"Update test (Top Pipe)"(){
        when:
            boolean result = topPipe.update(0)

        then:
            topPipe.getPosition() == new Position(18, 20)
            result == false
    }

    def"Update test limits (Bottom Pipe)"(){
        given:
            boolean result

        when:
        for(int i = 0; i < 11; i++)
            result = bottomPipe.update(0);

        then:
            bottomPipe.getPosition() == new Position(0, 20)
            result == true
    }



    def"Update test limits (Top Pipe)"(){
        given:
            boolean result

        when:
        for(int i = 0; i < 11; i++)
            result = topPipe.update(0);

        then:
            topPipe.getPosition() == new Position(0, 20)
            result == true
    }

    def"Draw test(Bottom Pipe)"(){
        when:
            bottomPipe.draw(screen)

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_,_,_)

    }

    def"Draw test(Top Pipe)"(){
        when:
            topPipe.draw(screen)

        then:
            1 * screen.setBackgroundColor(_)
        then:
            1 * screen.fillRectangle(_,_,_)

    }

    def"Collision Test (Top)"(){
        expect:
        topPipe.overlap(pos) == bool

        where:
        pos | bool
        new Position(18,0) | new Dimension(1,1) |false
        new Position(31,0) | new Dimension(1,1) |false
        new Position(25,11) | new Dimension(1,1) |false
        new Position(19,0) | new Dimension(1,1) |true
        new Position(29,0) | new Dimension(1,1) |true
        new Position(19,11) | new Dimension(1,1) |true
        new Position(29,11) | new Dimension(1,1) |true
        new Position(25,5) | new Dimension(1,1) | true

    }

    def"Collision Test (Bottom)"(){
        expect:
        bottomPipe.overlap(pos, dimensions) == bool

        where:
        pos | dimensions | bool
        new Position(19,0) | new Dimension(1,1) | false
        new Position(20,15) | new Dimension(1,1) |false
        new Position(30,40) | new Dimension(1,1) |false
        new Position(19,39) | new Dimension(1,1) |true
        new Position(29,39) | new Dimension(1,1) |true
        new Position(19,29) | new Dimension(1,1) |true
        new Position(29,29) | new Dimension(1,1) |true
        new Position(25,35) | new Dimension(1,1) |true

    }
}

