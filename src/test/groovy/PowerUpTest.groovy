import Controls.BottomPipe
import Controls.Dimension
import Controls.Faster
import Controls.Position
import Controls.TopPipe
import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification

class PowerUpTest extends Specification{

    private def Faster faster;
    private def screen;

    def setup(){

        screen = Mock(TextGraphics)
    }
    def"Update test for generality of Power ups"(){
        when:
        boolean result = faster.update(0)

        then:
        faster.getPosition() == new Position(18, 30)
        result == false
    }

    def"Draw test"(){
        when:
        faster.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)

    }

    def"Collision Test"(){
        expect:
        faster.overlap(pos) == bool

        where:
        pos  | bool
        new Position(18,0)  |false
        new Position(31,0)  |false
        new Position(25,11) |false
        new Position(19,0)  |true
        new Position(29,0)  |true
        new Position(19,10) |true
        new Position(29,10) |true
        new Position(25,5)  | true

    }


}
