package self.gorgol.engine.animation;

import self.gorgol.engine.physics.PhysicsBody;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class PhysicsMovementAnimation extends Animation {

    public enum State {
        IDLE_BOTTOM,
        IDLE_TOP,
        IDLE_RIGHT,
        IDLE_LEFT,
        BOTTOM,
        TOP,
        RIGHT,
        LEFT
    }


    public record StateFrames(State state, int framesCount) {

    }

    private final PhysicsBody body;

    private final Map<State, List<Image>> frames;

    private State state;

    private State lastState = State.BOTTOM;

    public PhysicsMovementAnimation(PhysicsBody body, BufferedImage resource, float frameDurationSec, StateFrames... states) {
        super(frameDurationSec);
        this.body = body;
        this.frames = new HashMap<>();
        prepareFrames(resource, Arrays.asList(states));
    }

    @Override
    public void update(float dt) {
        State oldState = state;
        resolveState();
        if (oldState != state) {
            index = 0;
            accumulator = 0;
        }

        accumulator += dt;
        var stateFrames = frames.get(state);
        while (accumulator >= frameDurationSec) {
            accumulator -= frameDurationSec;
            index = (index + 1) % stateFrames.size();
        }
    }

    @Override
    public Image getCurrentFrame() {
        return frames.get(state).get(index);
    }

    private void prepareFrames(BufferedImage resource, List<StateFrames> states) {
        if (states.size() != State.values().length) {
            throw new RuntimeException("wrong frame states for :" + State.class.getName() + ". states: " + states);
        }

        final int frameHeight = resource.getHeight() / states.size();
        final int frameWidth = resource.getWidth() / states
                .stream()
                .map(StateFrames::framesCount)
                .max(Comparator.comparingInt(n -> n))
                .orElseThrow();

        for (var entry : states) {
            List<Image> stateImages = new ArrayList<>();
            for (int i = 0; i < entry.framesCount; i++) {
                stateImages.add(resource.getSubimage(
                        i * frameWidth,
                        entry.state().ordinal() * frameHeight,
                        frameWidth,
                        frameHeight
                ));
            }
            frames.put(entry.state(), stateImages);
        }
    }

    private void resolveState() {
        float vx = body.getVx();
        float vy = body.getVy();
        float maxSpeed = body.getMaxSpeed();
        float EPSILON = maxSpeed * 0.25f;

        if (Math.abs(vx) < EPSILON) vx = 0;
        if (Math.abs(vy) < EPSILON) vy = 0;

        State newState;

        if (vx != 0) {
            newState = vx > 0 ? State.RIGHT : State.LEFT;
        } else if (vy != 0) {
            newState = vy > 0 ? State.BOTTOM : State.TOP;
        } else {
            switch (lastState) {
                case LEFT -> newState = State.IDLE_LEFT;
                case RIGHT -> newState = State.IDLE_RIGHT;
                case TOP -> newState = State.IDLE_TOP;
                default -> newState = State.IDLE_BOTTOM;
            }
        }

        if (newState != State.IDLE_LEFT && newState != State.IDLE_RIGHT &&
                newState != State.IDLE_TOP && newState != State.IDLE_BOTTOM) {
            lastState = newState;
        }

        state = newState;
    }
}
