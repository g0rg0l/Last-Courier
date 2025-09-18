package self.gorgol.business;

import self.gorgol.engine.animation.Animation;
import self.gorgol.engine.animation.PhysicsMovementAnimation;
import self.gorgol.engine.physics.PhysicsBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

public class Player  {

    private final PhysicsBody body;

    private final Animation bodyAnimation;

    public Player() {
        this.body = new PhysicsBody(0, 0, 128, 128);

        try {
            this.bodyAnimation = new PhysicsMovementAnimation(
                    body,
                    ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player_sprites.png"))),
                    0.25f,
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.BOTTOM, 4),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.TOP, 4),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.LEFT, 4),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.RIGHT, 4),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.IDLE_BOTTOM, 2),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.IDLE_TOP, 2),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.IDLE_LEFT, 2),
                    new PhysicsMovementAnimation.StateFrames(PhysicsMovementAnimation.State.IDLE_RIGHT, 2)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(
                bodyAnimation.getCurrentFrame(),
                (int) body.getX(), (int) body.getY(),
                (int) body.getWidth(), (int) body.getHeight(),
                null
        );
    }

    public void updatePosition(float dx, float dy, float dt) {
        if (dx != 0 || dy != 0) {
            body.accelerate(dx, dy, dt);
        }
        body.integrate(dt);
        bodyAnimation.update(dt);
    }

}
