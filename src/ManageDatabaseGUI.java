import javax.swing.*;
import java.awt.*;

public class ManageDatabaseGUI extends JFrame {
    private JPanel main;
    private JLabel titleLabel;
    private JButton backupButton;
    private JButton restoreButton;
    private static ManageDatabaseGUI j = new ManageDatabaseGUI();

    public ManageDatabaseGUI() {

    }

    public static void main(){
        j.setContentPane(new ManageDatabaseGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Manage Database");
        j.setPreferredSize(new Dimension(1280,720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
