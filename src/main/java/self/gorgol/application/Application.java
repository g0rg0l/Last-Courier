package self.gorgol.application;

import self.gorgol.engine.Engine;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Engine engine = new Engine();

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            TestPanel panel = new TestPanel();
            panel.setBackground(Color.GRAY);
            panel.requestFocusInWindow();
            panel.setPreferredSize(new Dimension(800, 600));

            frame.add(panel);
            frame.pack();

            engine.addUpdater(panel);
            engine.start();
        });
    }
}
