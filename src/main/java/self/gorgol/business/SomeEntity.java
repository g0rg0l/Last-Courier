package self.gorgol.business;

import self.gorgol.engine.animation.Animation;
import self.gorgol.engine.animation.StaticAnimation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

public class SomeEntity {

    private final Rectangle body;

    private final Animation animation;

    public SomeEntity() {
        this.body = new Rectangle(0, 0, 1024, 1024);

        try {
            this.animation = new StaticAnimation(
                    ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/some_sprites.png"))),
                    0.25f,
                    4
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(
                animation.getCurrentFrame(),
                (int) body.getX(), (int) body.getY(),
                (int) body.getWidth(), (int) body.getHeight(),
                null
        );
    }

    public void update(float dt) {
        animation.update(dt);
    }

}
