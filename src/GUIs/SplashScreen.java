package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

import static java.lang.Thread.sleep;

public class SplashScreen extends JFrame {
    private JPanel Main;
    private JLabel logoLabel;

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
            sleep(1500);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        j.dispose();
        LoginGUI.main();
    }

    private void createUIComponents() {
        logoLabel = new JLabel();
        try {
            Image img = ImageIO.read(new FileInputStream("data/logo.png")).getScaledInstance(250, 150, Image.SCALE_DEFAULT);
            logoLabel.setIcon(new ImageIcon(img));
        } catch (Exception ignored) {
        }
    }
}
