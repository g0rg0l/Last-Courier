package self.gorgol.application;

import self.gorgol.engine.IUpdater;
import self.gorgol.input.Context;
import self.gorgol.input.InputManager;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class TestPanel extends JPanel implements IUpdater {

    public TestPanel() {
        super();
        InputManager.getInstance().registerBindings(
                this,
                KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D
        );
    }

    @Override
    public void update(float dt) {
        InputManager input = InputManager.getInstance();
        if (input.getContext() == Context.GLOBAL) {
            if (input.isPressed(KeyEvent.VK_W)) {
                System.out.println("W");
            }
            if (input.isPressed(KeyEvent.VK_S)) {
                System.out.println("S");
            }
            if (input.isPressed(KeyEvent.VK_D)) {
                System.out.println("D");
            }
            if (input.isPressed(KeyEvent.VK_A)) {
                System.out.println("A");
            }
        }
    }
}
