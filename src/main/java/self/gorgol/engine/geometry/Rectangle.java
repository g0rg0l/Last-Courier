package self.gorgol.engine.geometry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an axis-aligned rectangle in 2D space.
 * <p>
 * Provides basic geometry operations such as computing its center,
 * intersection checks with other rectangles, and point containment checks.
 * </p>
 */
@Getter
@Setter
@AllArgsConstructor
public class Rectangle {

    /** The x-coordinate of the rectangle's top-left corner. */
    protected float x;

    /** The y-coordinate of the rectangle's top-left corner. */
    protected float y;

    /** The width of the rectangle. */
    protected float width;

    /** The height of the rectangle. */
    protected float height;

    /**
     * Returns the geometric center point of this rectangle.
     *
     * @return a {@link Point} representing the center of the rectangle
     */
    public Point getCenter() {
        return new Point(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    /**
     * Checks if this rectangle intersects with another rectangle.
     * <p>
     * Two rectangles intersect if they overlap horizontally and vertically.
     * </p>
     *
     * @param other the other rectangle to test against
     * @return {@code true} if the rectangles intersect, {@code false} otherwise
     */
    public boolean intersects(Rectangle other) {
        return getX() + getWidth()  >= other.getX() && getX() < other.getX() + other.getWidth() &&
                getY() + getHeight() >= other.getY() && getY() <= other.getY() + other.getHeight();
    }

    /**
     * Checks if this rectangle contains the given point.
     *
     * @param point the point to check
     * @return {@code true} if the point lies within this rectangle, {@code false} otherwise
     */
    public boolean contains(Point point) {
        return point.getX() >= getX() && point.getX() <= getX() + getWidth() &&
                point.getY() >= getY() && point.getY() <= getY() + getHeight();
    }

    @Override
    public String toString() {
        return String.format("Rectangle{x=%f, y=%f, width=%f, height=%f}", x, y, width, height);
    }
}