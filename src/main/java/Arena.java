import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.*;

public class Arena {
    private int width;
    private int height;
    private final Bird bird;
    private List<Pipe> pipes;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        bird = new Bird(new Position(width/2,height/2),new Dimension(1,1), 1,5);
        pipes = new ArrayList<Pipe>();
        createPipes();
    }

    public Arena(int width, int height, Bird bird){
        this.height = height;
        this.width = width;
        this.bird = bird;
        pipes = new ArrayList<Pipe>();
    }

    public void addPipe(Pipe pipe){
        pipes.add(pipe);
    }


    public void createPipes() {
        Random random = new Random();
        int heightPipe = random.nextInt(height/2) + 7;
        Pipe bottomPipe= new BottomPipe(new Position(width,heightPipe+7),  new Dimension(height-heightPipe+7,7),1);
        pipes.add(bottomPipe);
        Pipe pipeUp= new TopPipe(new Position(width,0),  new Dimension(heightPipe,7),1);
        pipes.add(pipeUp);
    }

    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        bird.draw(screen);
        for(Pipe pipe : pipes)
            pipe.draw(screen);
    }

    public boolean update(boolean slapBird){
        if(slapBird){
            bird.slap(0);
        }else{
            bird.update(height-1);
        }

        if(bird.isDead()){
            return false;
        }

        List<Pipe> newPipes = new ArrayList<Pipe>();
        for(Pipe pipe : pipes){
            if(pipe.update(0)){
                newPipes.add(pipe);
            }
        }
        pipes = newPipes;
        return true;
    }


    public boolean verifyPipeCollisions(){
        for(Pipe pipe: pipes)
            if(pipe.overlap(bird.getPosition(), bird.getDimension())){
                return true;
            }
        return false;
    }

    public int getNumberPipes(){
        return pipes.size();
    }
}
