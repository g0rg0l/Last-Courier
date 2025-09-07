package self.gorgol.input;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public final class InputManager {

    @Getter
    private static final InputManager instance = new InputManager();

    private final boolean[] keys = new boolean[256];
    private final boolean[] justPressedKeys = new boolean[256];

    @Getter
    @Setter
    private Context context = Context.GLOBAL;

    private InputManager() {  }

    private void setKeyPressed(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            if (!keys[keyCode]) {
                justPressedKeys[keyCode] = true;
            }
            keys[keyCode] = true;
        }
    }

    private void setKeyReleased(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    public boolean isPressed(int keyCode) {
        return keyCode >= 0 && keyCode < keys.length && keys[keyCode];
    }

    public boolean wasJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) return false;
        boolean result = justPressedKeys[keyCode];
        justPressedKeys[keyCode] = false;
        return result;
    }

    //TODO: replace for single registration now: from code, later: from keys.properties
    public void registerBindings(JComponent component, int... keys) {
        for (int key : keys) {
            String pressed = "pressed " + key;
            String released = "released " + key;

            component
                    .getInputMap(WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(key, 0, false), pressed);
            component
                    .getInputMap(WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(key, 0, true), released);

            component.getActionMap().put(pressed, new AbstractAction() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    setKeyPressed(key);
                }
            });

            component.getActionMap().put(released, new AbstractAction() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    setKeyReleased(key);
                }
            });
        }
    }

}
