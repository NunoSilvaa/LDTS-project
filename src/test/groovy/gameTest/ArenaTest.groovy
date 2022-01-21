package gameTest

import flappyBird.entities.enemies.Bee
import flappyBird.entities.enemies.DarthVader
import flappyBird.entities.pipes.Pipe
import flappyBird.entities.powerups.Faster
import flappyBird.game.Arena
import flappyBird.entities.*
import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.game.states.ArenaState
import flappyBird.game.states.FasterState
import flappyBird.game.states.NormalState
import flappyBird.move.Diagonal
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import spock.lang.Specification

class ArenaTest extends Specification{
    private Arena arena;
    private TextGraphics screen;
    private ArenaState state;

    def setup(){
        arena = new Arena(42,42)
        screen = Mock(TextGraphics)
    }

    def"Get Instance - If(true)"(){
        when:
        def result = arena.getInstance()
        then:
        result instanceof Bird
    }

    def"Get Instance - If(False)"(){
        given:
        def specialArena = new Arena(10,10,Mock(Bird.class))
        when:
        def result = specialArena.getInstance()
        then:
        result instanceof Bird
    }

    def"Draw Test"(){
        given:
        def arena = new Arena(42,42)
        def screen = Mock(TextGraphics)

        when:
        arena.draw(screen)

        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_,_,_)
        then:
        1 * screen.setBackgroundColor(_)
        1 * screen.fillRectangle(_,_,_)
        then:
        2 * screen.setBackgroundColor(_)
        2 * screen.fillRectangle(_,_,_)
    }



    def"Creating Pipes Test"(){
        given:
        def arena = new Arena(42,42)

        int start = arena.getNumberPipes();
        when:
        arena.addPipes()
        then:
        arena.getNumberPipes() - start==2
    }

    def"Collide Entities Test - (True)"(){
        given:
        def rBird = Mock(Rectangle.class)
        def bird = Spy(Bird, constructorArgs: [rBird, 2,new Vertical(),2,2])

        def arena = new Arena(42,42,bird)

        def rPipe1 = Mock(Rectangle.class)
        def pipe1 = new Pipe(rPipe1, 2, new Horizontal())
        def rPipe2 = Mock(Rectangle.class)
        def pipe2 = new Pipe(rPipe2, 2, new Horizontal())

        def rEnemy1 = Mock(Rectangle.class)
        def enemy1 = new DarthVader(rEnemy1, 2, new Diagonal())
        def rEnemy2 = Mock(Rectangle.class)
        def enemy2 = new DarthVader(rEnemy2, 2, new Diagonal())

        def rPowerUp = Mock(Rectangle.class)
        def powerUp = new Faster(rPowerUp, 2, new Diagonal())


        rPipe1.intersect(rBird) >> true
        rPipe2.intersect(rBird) >> true
        rEnemy1.intersect(rBird) >> true
        rEnemy2.intersect(rBird) >> true
        rBird.intersect(rPowerUp) >> true
        arena.injectPipe(pipe1)
        arena.injectPipe(pipe2)
        arena.injectEnemy(enemy1)
        arena.injectEnemy(enemy2)
        arena.injectPowerUps(powerUp)

        when:
        arena.collideEntities()

        then:
        4 * bird.decreaseLives(_)
        arena.getState() instanceof FasterState
    }


    def"Collide Entities Test - (False)"(){
        given:
        def rBird = Mock(Rectangle.class)
        def bird = Spy(Bird, constructorArgs: [rBird, 2,new Vertical(),2,2])

        def arena = new Arena(42,42,bird)

        def rPipe1 = Mock(Rectangle.class)
        def pipe1 = new Pipe(rPipe1, 2, new Horizontal())
        def rPipe2 = Mock(Rectangle.class)
        def pipe2 = new Pipe(rPipe2, 2, new Horizontal())

        def rEnemy1 = Mock(Rectangle.class)
        def enemy1 = new DarthVader(rEnemy1, 2, new Diagonal())
        def rEnemy2 = Mock(Rectangle.class)
        def enemy2 = new DarthVader(rEnemy2, 2, new Diagonal())

        def rPowerUp = Mock(Rectangle.class)
        def powerUp = new Faster(rPowerUp, 2, new Diagonal())


        rPipe1.intersect(rBird) >> false
        rPipe2.intersect(rBird) >> false
        rEnemy1.intersect(rBird) >> false
        rEnemy2.intersect(rBird) >> false
        rBird.intersect(rPowerUp) >> false
        arena.injectPipe(pipe1)
        arena.injectPipe(pipe2)
        arena.injectEnemy(enemy1)
        arena.injectEnemy(enemy2)
        arena.injectPowerUps(powerUp)

        when:
        arena.collideEntities()

        then:
        0 * bird.decreaseLives(_)
        arena.getState() instanceof NormalState
    }


    def"Update Test - if statement (slapping & moving)"() {
        given:
        def bird = Mock(Bird.class)
        def arena = new Arena(42, 42, bird)

        when:
        arena.update(a)

        then:
        b * bird.slap()
        c * bird.move()

        where:
        a | b | c
        true | 1 | 0
        false | 0 | 1

    }

    def"Update Test - for loop(False)"() {
        given:
        def bird = new Bird(new Position(10,10),new Dimension(10,10), 1, new Vertical(),2,2)
        def arena = new Arena(42,42,bird)
        def enemy = new Bee(new Position(20,20),new Dimension(10,10), 1, new Diagonal())
        def pipe = new Pipe(new Position(30,30), new Dimension(10,10),1,new Horizontal())
        def powerUp = new Faster(new Position(40,40), new Dimension(10,10),1,new Horizontal())
        arena.injectPowerUps(powerUp)
        arena.injectEnemy(enemy)
        arena.injectPipe(pipe)

        when:
        arena.update(false)

        then:
        bird.getPosition() == new Position(10,11)
        enemy.getPosition() == new Position(19,21)
        pipe.getPosition() == new Position(29,30)
        powerUp.getPosition() == new Position(39,40)

    }

    def"Update Test - for loop(True)"() {
        given:
        def bird = new Bird(new Position(10,10),new Dimension(10,10), 1, new Vertical(),2,2)
        def arena = new Arena(42,42,bird)
        def enemy = new Bee(new Position(20,20),new Dimension(10,10), 1, new Diagonal())
        def pipe = new Pipe(new Position(30,30), new Dimension(10,10),1,new Horizontal())
        def powerUp = new Faster(new Position(40,40), new Dimension(10,10),1,new Horizontal())
        arena.injectPowerUps(powerUp)
        arena.injectEnemy(enemy)
        arena.injectPipe(pipe)

        when:
        arena.update(true)

        then:
        bird.getPosition() == new Position(10,8)
        enemy.getPosition() == new Position(19,21)
        pipe.getPosition() == new Position(29,30)
        powerUp.getPosition() == new Position(39,40)

    }


    def"Update - Edge case Bird floor"(){
        given:
        def bird = new Bird(new Position(20,40), new Dimension(1,1), 1, new Vertical(), 1 ,2)
        def arena = new Arena(42, 42,bird)

        when:
        arena.update(false)

        then:
        bird.getLives() == 1
        bird.getPosition() == new Position(20,21)

    }

    def"Update - Edge case Bird ceiling"(){
        given:
        def bird = new Bird(new Position(20,0), new Dimension(1,1), 1, new Vertical(), 1 ,2)
        def arena = new Arena(42, 42,bird)

        when:
        arena.update(true)

        then:
        bird.getLives() == 2
        bird.getPosition() == new Position(20,0)

    }

    def"Game Over test"(){
        given:
        def bird = Mock(Bird.class)
        def arena = new Arena(42, 42, bird)
        bird.isDead() >> a

        expect:
        arena.gameOver() == b

        where:
        a | b
        true | true
        false | false
    }
}