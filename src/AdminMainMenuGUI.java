import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class AdminMainMenuGUI extends JFrame{
    private JButton usersButton;
    private JPanel Main;
    private JButton databaseButton;
    private JLabel titleLabel;
    private JLabel usersLabel;
    private JLabel databaseLabel;
    public static AdminMainMenuGUI j = new AdminMainMenuGUI();

    public static void main() {
        j.setContentPane(new AdminMainMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Admin Dashboard");
        j.setPreferredSize(new Dimension(1280,720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        usersButton = new JButton();
        databaseButton = new JButton();

        try {
            Image img = ImageIO.read(new FileInputStream("data/usericon.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image dtbImg = ImageIO.read(new FileInputStream("data/databaseicon.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            usersButton.setIcon(new ImageIcon(img));
            databaseButton.setIcon(new ImageIcon(dtbImg));
        } catch (Exception ignored) {
        }
        // Make the rest of the button invisible
        usersButton.setBorder(null);
        usersButton.setBorderPainted(false);
        usersButton.setBorder(null);
        usersButton.setMargin(new Insets(0, 0, 0, 0));
        usersButton.setContentAreaFilled(false);
        usersButton.setFocusPainted(false);
        databaseButton.setBorder(null);
        databaseButton.setBorderPainted(false);
        databaseButton.setBorder(null);
        databaseButton.setMargin(new Insets(0, 0, 0, 0));
        databaseButton.setContentAreaFilled(false);
        databaseButton.setFocusPainted(false);
    }
}
