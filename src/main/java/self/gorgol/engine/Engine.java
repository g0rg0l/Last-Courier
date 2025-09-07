package self.gorgol.engine;

import java.util.ArrayList;
import java.util.List;

public final class Engine {

    private final Thread gameLoop;

    private final List<IUpdater> updaters;

    private final List<IRenderer> renderers;

    public Engine() {
        this.gameLoop = setupGameLoop();
        this.updaters = new ArrayList<>();
        this.renderers = new ArrayList<>();
    }

    private void update(float dt) {
        updaters.forEach(updater -> updater.update(dt));
    }

    private void render() {
        renderers.forEach(IRenderer::render);
    }

    private Thread setupGameLoop() {
        return new Thread(() -> {
            final int FRAMES_PER_SECOND = 144;
            final long TIME_BETWEEN_UPDATES = 1000000000 / FRAMES_PER_SECOND;
            final int MAX_UPDATES_BETWEEN_RENDER = 1;

            long lastUpdateTime = System.nanoTime();
            long currTime = System.currentTimeMillis();

            while (true) {
                long now = System.nanoTime();
                long elapsedTime = System.currentTimeMillis() - currTime;
                currTime += elapsedTime;

                /* Updating (can be more than 1 times if that needed) */
                int updateCount = 0;
                while (now - lastUpdateTime >= TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BETWEEN_RENDER) {
                    float dt = (float) elapsedTime / 1000;
                    update(dt);
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                /* Waiting for needed time to this frame */
                if (now - lastUpdateTime >= TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                /* Rendering */
                render();

                /* Yielding thread while possible */
                long lastRenderTime = now;
                while (now - lastRenderTime < TIME_BETWEEN_UPDATES && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();
                    now = System.nanoTime();
                }
            }
        });
    }

    public void addRenderer(IRenderer renderer) {
        renderers.add(renderer);
    }

    public void addUpdater(IUpdater updater) {
        updaters.add(updater);
    }

    public void start() {
        if (gameLoop.getState() != Thread.State.NEW) {
            throw new IllegalStateException("Engine is not ready to be started. Current state=" + gameLoop.getState());
        }
        gameLoop.start();
    }
}
