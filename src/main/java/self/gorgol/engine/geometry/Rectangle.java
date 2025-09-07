package self.gorgol.engine.geometry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rectangle {

    protected float x;

    protected float y;

    protected float width;

    protected float height;

    public Point getCenter() {
        return new Point(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    public boolean intersects(Rectangle other) {
        return getX() + getWidth()  >= other.getX() && getX() < other.getX() + other.getWidth() &&
                getY() + getHeight() >= other.getY() && getY() <= other.getY() + other.getHeight();
    }

    public boolean contains(Point point) {
        return point.getX() >= getX() && point.getX() <= getX() + getWidth() &&
                point.getY() >= getY() && point.getY() <= getY() + getHeight();
    }

}
