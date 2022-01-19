package gameTest

import flappyBird.entities.enemies.Bee
import flappyBird.entities.enemies.DarthVader
import flappyBird.entities.pipes.Pipe
import flappyBird.entities.powerups.Faster
import flappyBird.entities.powerups.Health
import flappyBird.entities.powerups.Powerup
import flappyBird.entities.weapons.LaserSword
import flappyBird.entities.weapons.Sting
import flappyBird.entities.weapons.Weapon
import flappyBird.game.Arena
import flappyBird.entities.*
import flappyBird.entities.pipes.*
import com.googlecode.lanterna.graphics.TextGraphics
import flappyBird.game.states.ArenaState
import flappyBird.entities.enemies.Enemy
import flappyBird.move.Horizontal
import flappyBird.move.Vertical
import flappyBird.rectangle.Dimension
import flappyBird.rectangle.Position
import flappyBird.rectangle.Rectangle
import move.HorizontalTest
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
        def result = arena.getInstance()
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
        def bird = Mock(Bird.class)
        def rBird = Mock(Rectangle.class)
        bird.setRectangle(rBird)

        def arena = new Arena(42,42,bird)

        def rPipe1 = Mock(Rectangle.class)
        def rPipe2 = Mock(Rectangle.class)
        def pipe1 = Spy(BottomPipe.class, constructorArgs: [rPipe1, 2, new Horizontal()])
        def pipe2 = Spy(TopPipe.class, constructorArgs: [rPipe2, 2, new Horizontal()])
        arena.injectPipe(pipe1)
        arena.injectPipe(pipe2)


        def weapon = Mock(LaserSword.class)
        def rEnemy1 = Mock(Rectangle.class)
        def rEnemy2 = Mock(Rectangle.class)
        def enemy1 = Spy(DarthVader.class, constructorArgs: [rEnemy1, 2, new Horizontal()])
        def enemy2 = Spy(DarthVader.class, constructorArgs: [rEnemy2, 2, new Horizontal()])
        enemy1.setWeapon(weapon)
        enemy2.setWeapon(weapon)
        arena.injectEnemy(enemy1)
        arena.injectEnemy(enemy2)

        def rPowerUp1 = Mock(Rectangle.class)
        def rPowerUp2 = Mock(Rectangle.class)
        def powerUp1 = Spy(Faster.class, constructorArgs: [rPowerUp1, 2, new Horizontal()])
        def powerUp2 = Spy(Faster.class, constructorArgs: [rPowerUp2, 2, new Horizontal()])
        arena.injectPowerUps(powerUp1)
        arena.injectPowerUps(powerUp2)


        pipe1.intersect(rBird) >> true
        pipe2.intersect(rBird) >> false
        enemy1.intersect(rBird) >> true
        enemy2.intersect(rBird) >> false
        powerUp1.intersect(rBird) >> true
        powerUp2.intersect(rBird) >> false

        when:
        arena.collideEntities()

        then:
        1 * bird.decreaseLives(_)
        1 * weapon.attackBird(_)
        1 * arena.setState(_)


    }

    def"Collide Entities Test - (False)"(){
        given:
        def bird = Mock(Bird.class)
        def rBird = Mock(Rectangle.class)
        bird.setRectangle(rBird)

        def arena = new Arena(42,42,bird)

        def rPipe1 = Mock(Rectangle.class)
        def rPipe2 = Mock(Rectangle.class)
        def pipe1 = Spy(BottomPipe.class, constructorArgs: [rPipe1, 2, new Horizontal()])
        def pipe2 = Spy(TopPipe.class, constructorArgs: [rPipe2, 2, new Horizontal()])
        arena.injectPipe(pipe1)
        arena.injectPipe(pipe2)


        def weapon = Mock(LaserSword.class)
        def rEnemy1 = Mock(Rectangle.class)
        def rEnemy2 = Mock(Rectangle.class)
        def enemy1 = Spy(DarthVader.class, constructorArgs: [rEnemy1, 2, new Horizontal()])
        def enemy2 = Spy(DarthVader.class, constructorArgs: [rEnemy2, 2, new Horizontal()])
        enemy1.setWeapon(weapon)
        enemy2.setWeapon(weapon)
        arena.injectEnemy(enemy1)
        arena.injectEnemy(enemy2)

        def rPowerUp1 = Mock(Rectangle.class)
        def rPowerUp2 = Mock(Rectangle.class)
        def powerUp1 = Spy(Faster.class, constructorArgs: [rPowerUp1, 2, new Horizontal()])
        def powerUp2 = Spy(Faster.class, constructorArgs: [rPowerUp2, 2, new Horizontal()])
        arena.injectPowerUps(powerUp1)
        arena.injectPowerUps(powerUp2)


        pipe1.intersect(rBird) >> false
        pipe2.intersect(rBird) >> false
        enemy1.intersect(rBird) >> false
        enemy2.intersect(rBird) >> false
        powerUp1.intersect(rBird) >> false
        powerUp2.intersect(rBird) >> false

        when:
        arena.collideEntities()

        then:
        0 * bird.decreaseLives(_)
        0 * weapon.attackBird(_)
        0 * arena.setState(_)


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

    def"Update Test - for loop"() {
        given:
        boolean result
        def pipe = Mock(Pipe.class)
        def enemy = Mock(Enemy.class)
        def arena = new Arena(42, 42)

        when:
        arena.update(false)

        then:
        arena.getNumberPipes() * pipe.move()
        arena.getEnemies().size() * enemy.move()
    }

    def"U+date - Edge Case Pipe"(){
        given:
        def arena = new Arena(42, 42)
        def rec1 = new Rectangle(new Position(1,1), new Dimension(10,1))
        def pipe = Spy(Pipe,constructorArgs:[rec1, 1, new Horizontal(), 1, 1])
        arena.injectPipe(pipe)
        int initialPipeNum = arena.getNumberPipes();

        when:
        arena.update(false)

        then:
        initialPipeNum - arena.getNumberPipes() == 1


    }

    def"Update - Edge case Bird floor"(){
        given:
        def rec1 = new Rectangle(new Position(1,1), new Dimension(1,1))
        def bird = Spy(Bird,constructorArgs:[rec1, 1, new Vertical(), 1, 42])
        def arena = new Arena(42, 42,bird)

        when:
        arena.update(true)

        then:
        1*bird.decreaseLives()

    }

    def"Update - Edge case Bird ceiling"(){
        given:
        def rec1 = new Rectangle(new Position(1,1), new Dimension(1,1))
        def bird = Spy(Bird,constructorArgs:[rec1, 1, new Vertical(), 1, 1])
        def arena = new Arena(42, 42,bird)

        when:
        arena.update(false)

        then:
        1*bird.decreaseLives()

    }

    def"Bird collides against an powerup"(){
        given:
        def rec1 = new Rectangle(new Position(1,1), new Dimension(1,1))
        def bird = Spy(Bird,constructorArgs:[rec1, 1, new Vertical(), 1, 1])
        def arena = new Arena(42, 42,bird)
        def rec2 = new Rectangle(new Position(1,1), new Dimension(1,1))
        def powerup = Spy(Faster,constructorArgs:  [rec2, 1, new Horizontal(), 1, 1])
        arena.injectPowerUps(powerup)

        when:
        arena.collideEntities()

        then:
        1*arena.setState(_)



    }

    def"Bird collides against a pipe"(){
        given:
        def rec1 = new Rectangle(new Position(1,1), new Dimension(1,1))
        def bird = Spy(Bird,constructorArgs:[rec1, 1, new Vertical(), 1, 1])
        def arena = new Arena(42, 42,bird)
        def rec2 = new Rectangle(new Position(1,1), new Dimension(11,2))
        def pipe = Spy(Pipe,constructorArgs:  [rec2, 1, new Horizontal(), 1, 1])
        arena.injectPipe(pipe)

        when:
        arena.collideEntities()

        then:
        1*bird.decreaseLives(_)
    }

    def"Bird collides against an enemy"(){
        given:
        def rec1 = new Rectangle(new Position(1,1), new Dimension(1,1))
        def bird = Spy(Bird,constructorArgs:[rec1, 1, new Vertical(), 1, 1])
        def arena = new Arena(42, 42,bird)
        def rec2 = new Rectangle(new Position(1,1), new Dimension(11,2))
        def bee = Spy(Bee,constructorArgs:  [rec2, 1, new Horizontal(), 1, 1])
        arena.injectEnemy(bee)

        when:
        arena.collideEntities()

        then:
        1*bird.decreaseLives(_)
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
