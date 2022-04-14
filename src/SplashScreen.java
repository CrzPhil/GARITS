import GUIs.FindImages;
import GUIs.LoginGUI;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/*
    Main Class for GARITS Software Solution - Group 4.
    Run the main function and log in as one of the following users to test the different Dashboards / Privileges:

    Administrator ->    SYSDBA   :   Masterkey
    Receptionist  ->    Penelope :   Pinkmobile
    Foreperson    ->    Sunny    :   Attitude
    Franchisee    ->    Glynne   :   Gnasher
    Mechanic      ->    Gavin    :   LondonWeight

    Our Database is hosted on a third-party Server (linode).
    Credentials are found in source code, but connection is automatic.
 */

public class SplashScreen extends JFrame {
    private JPanel Main;
    private JLabel logoLabel;

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

    // Create Logo for landing screen
    private void createUIComponents() {
        logoLabel = new JLabel();
        try {
            Image icon = FindImages.getImageDisplay("logo").getScaledInstance(250, 150, Image.SCALE_DEFAULT);
            logoLabel.setIcon(new ImageIcon(icon));
        } catch (Exception ignored) {
        }
    }
}
