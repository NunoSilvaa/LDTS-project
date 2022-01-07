import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bird extends Entities {
    private int slapHeight;
    private boolean dead;


    public Bird(Position position, Dimension dimension, int speed, int slapHeight) {
        super(position, dimension, speed);
        this.slapHeight = slapHeight;
        dead = false;
    }

    @Override
    public boolean update(int limit) {


        if (position.getY() + speed > limit) {
            dead = true;
            return true;
        }

        if (position.getY() < 0) {
            position.setY(0);
            return true;
        }

        position.updateY(speed);
        return false;
    }


    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#8B0000"));
        screen.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(dimension.getWidth(), dimension.getHeight()), ' ');
    }

    public void slap(int limit) {
        if (position.getY() - slapHeight >= limit)
            position.updateY(-slapHeight);
        else
            position.setY(limit);
    }

    public boolean isDead() {return dead;}
}

