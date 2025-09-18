package self.gorgol.engine.animation;


import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public abstract class Animation {

    protected final float frameDurationSec;

    protected int index;

    protected float accumulator;

    public abstract void update(float dt);

    public abstract Image getCurrentFrame();

}
