package GUIs;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class SplashScreen extends JFrame {
    private JPanel Main;
    private JLabel logoLabel;
    //private static URL urlLogo = SplashScreen.class.getResource("/data/" + "logo" + ".png");

    public static void main(String args[]) {
        SplashScreen j = new SplashScreen();
        j.setContentPane(new SplashScreen().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("GARITS");

        Image icon = FindImages.getImageLogo();
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
            Image icon = FindImages.getImageDisplay("logo").getScaledInstance(250, 150, Image.SCALE_DEFAULT);
            logoLabel.setIcon(new ImageIcon(icon));
        } catch (Exception ignored) {
        }
    }
}
