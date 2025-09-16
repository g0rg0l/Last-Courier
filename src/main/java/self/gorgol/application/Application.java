package self.gorgol.application;

import self.gorgol.engine.Engine;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Engine engine = new Engine();

            JFrame frame = new JFrame();
            frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setTitle("Last Courier");
            frame.setUndecorated(true);
            frame.setVisible(true);

            TestPanel panel = new TestPanel();
            frame.add(panel);

            frame.pack();

            engine.addUpdater(panel);
            engine.addRenderer(panel);
            engine.start();
        });
    }
}
