package self.gorgol.engine.physics;

import lombok.Getter;
import lombok.Setter;
import self.gorgol.engine.geometry.Rectangle;

/**
 * Represents a simple 2D physics body with rectangular bounds.
 * <p>
 * This body supports basic acceleration, friction (deceleration),
 * maximum speed clamping, and position integration.
 * </p>
 */
@Getter
public class PhysicsBody extends Rectangle {

    @Setter
    protected float acceleration = 2400f;

    @Setter
    protected float friction = 1800f;

    @Setter
    protected float maxSpeed = 250f;

    protected float vx;
    protected float vy;


    public PhysicsBody(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * Applies acceleration to the body in the given direction.
     * <p>
     * The direction vector (dx, dy) is normalized internally. Acceleration is then applied
     * proportionally to {@link #acceleration} and the elapsed time {@code dt}.
     * The resulting velocity is clamped to {@link #maxSpeed}.
     * </p>
     *
     * @param dx the horizontal input direction (-1 to 1, can be not normalized)
     * @param dy the vertical input direction (-1 to 1, can be not normalized)
     * @param dt the elapsed time since last update (in seconds)
     */
    public void accelerate(float dx, float dy, float dt) {
        float length = (float) Math.sqrt(dx*dx + dy*dy);
        if (length > 0) {
            dx /= length;
            dy /= length;
            vx += dx * acceleration * dt;
            vy += dy * acceleration * dt;

            float currentSpeed = (float) Math.sqrt(vx * vx + vy * vy);
            if (currentSpeed > maxSpeed) {
                float scale = maxSpeed / currentSpeed;
                vx *= scale;
                vy *= scale;
            }
        }
    }

    /**
     * Integrates the current velocity into the position of the body.
     * <p>
     * This updates the body's position according to its velocity,
     * then applies friction to gradually slow it down.
     * </p>
     *
     * @param dt the elapsed time since last update (in seconds)
     */
    public void integrate(float dt) {
        setX(getX() + vx * dt);
        setY(getY() + vy * dt);

        float speed = (float) Math.sqrt(vx * vx + vy * vy);
        if (speed > 0) {
            float decel = friction * dt;
            speed = Math.max(0, speed - decel);
            float scale = speed / (float) Math.sqrt(vx * vx + vy * vy);
            vx *= scale;
            vy *= scale;

            if (Math.abs(vx) < 0.01) vx = 0;
            if (Math.abs(vy) < 0.01) vy = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("PhysicsBody{max speed=%f, friction=%f, acceleration=%f, body=%s}",
                maxSpeed,
                friction,
                acceleration,
                super.toString()
        );
    }
}
