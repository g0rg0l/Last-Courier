package self.gorgol.engine.geometry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a point in 2D space with x and y coordinates.
 * <p>
 * This class is a simple data holder for coordinates and provides
 * a formatted string representation.
 * </p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    /** The x-coordinate of this point. */
    protected float x;

    /** The y-coordinate of this point. */
    protected float y;

    @Override
    public String toString() {
        return String.format("Point{x=%f, y=%f}", x, y);
    }
}
