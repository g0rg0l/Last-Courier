package self.gorgol.engine.animation;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class StaticAnimation extends Animation {

    private final List<Image> frames;

    private final int framesNumber;

    public StaticAnimation(BufferedImage resource, float frameDurationSec, int framesNumber) {
        super(frameDurationSec);
        this.framesNumber = framesNumber;
        this.frames = new ArrayList<>();
        prepareFrames(resource);
    }

    @Override
    public void update(float dt) {
        accumulator += dt;
        while (accumulator >= frameDurationSec) {
            accumulator -= frameDurationSec;
            index = (index + 1) % frames.size();
        }
    }

    @Override
    public Image getCurrentFrame() {
        return frames.get(index);
    }

    private void prepareFrames(BufferedImage resource) {
        final int frameHeight = resource.getHeight();
        final int frameWidth = resource.getWidth() / framesNumber;

        for (int i = 0; i < framesNumber; i++) {
            frames.add(resource.getSubimage(
                    i * frameWidth,
                    0,
                    frameWidth,
                    frameHeight
            ));
        }
    }
}
