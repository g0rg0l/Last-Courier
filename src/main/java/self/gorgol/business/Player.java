package self.gorgol.business;

import self.gorgol.engine.physics.PhysicsBody;

import java.awt.*;

public class Player  {

    private final PhysicsBody hitbox = new PhysicsBody(0, 0, 50, 50);

    public void render(Graphics graphics) {
        graphics.drawRect((int) hitbox.getX(), (int) hitbox.getY(), (int) hitbox.getWidth(), (int) hitbox.getHeight());
    }

    public void updatePosition(float dx, float dy, float dt) {
        if (dx != 0 || dy != 0) {
            hitbox.accelerate(dx, dy, dt);
        }
        hitbox.integrate(dt);
    }

}
