package self.gorgol.application;

import self.gorgol.business.Player;
import self.gorgol.engine.IRenderer;
import self.gorgol.engine.IUpdater;
import self.gorgol.input.Context;
import self.gorgol.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TestPanel extends JPanel implements IUpdater, IRenderer {

    private final Player player = new Player();;

    public TestPanel() {
        super();
        setupKeyBindings();
    }

    @Override
    public void update(float dt) {
        InputManager input = InputManager.getInstance();
        if (input.getContext() == Context.GLOBAL) {
            float dx = 0, dy = 0;
            if (input.isPressed(KeyEvent.VK_W)) dy -= 1;
            if (input.isPressed(KeyEvent.VK_S)) dy += 1;
            if (input.isPressed(KeyEvent.VK_A)) dx -= 1;
            if (input.isPressed(KeyEvent.VK_D)) dx += 1;
            player.updatePosition(dx, dy, dt);
        }
    }

    @Override
    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.render(g);
    }

    private void setupKeyBindings() {
        InputManager.getInstance().registerBindings(this,
                KeyEvent.VK_W,
                KeyEvent.VK_A,
                KeyEvent.VK_S,
                KeyEvent.VK_D
        );
    }
}
