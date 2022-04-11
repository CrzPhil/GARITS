import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class SplashScreen extends JFrame {
    private JPanel Main;

    public static void main(String args[]) {
        SplashScreen j = new SplashScreen();
        j.setContentPane(new SplashScreen().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("GARITS");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        try {
            sleep(500);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        j.dispose();
        LoginGUI.main();
    }
}
