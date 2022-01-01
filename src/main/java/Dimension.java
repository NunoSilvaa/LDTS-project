import java.util.Objects;

public class Dimension {
    private int height;
    private int length;

    public Dimension(int height, int length) {
        this.height = height;
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return height == dimension.height && length == dimension.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, length);
    }
}
