import com.googlecode.lanterna.graphics.TextGraphics
import spock.lang.Specification


public class ArenaTest {
    private Arena arena
    private TextGraphics screen

    def setup(){
        arena = new Arena(40,20)
        screen = Mock(TextGraphics)
    }

    def"Draw Test"(){
        when:
        arena.draw(screen)
        then:
        1 * screen.setBackgroundColor(_)
        then:
        1 * screen.fillRectangle(_,_,_)
        then:
        1 * bird.draw()
        then:
        2 * pipes.draw()
    }

    def"Getting Number of Pipes"(){

    }

    def"Creating Pipes Test"(){
        when:
        arena.createPipes()
        then:


    }

    def"Collision of all Pipes Test"(){

    }

    def"Update Test"(){

    }




}
