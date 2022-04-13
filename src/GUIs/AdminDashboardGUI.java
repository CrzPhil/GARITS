package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class AdminDashboardGUI extends JFrame{
    private JButton usersButton;
    private JPanel Main;
    private JButton databaseButton;
    private JLabel titleLabel;
    private JLabel usersLabel;
    private JLabel databaseLabel;
    private JButton logOutButton;
    public static AdminDashboardGUI j = new AdminDashboardGUI();

    public static void main() {
        j.setContentPane(new AdminDashboardGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Admin Dashboard");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    public AdminDashboardGUI() {
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountMenuGUI.main();
            }
        });
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ManageDatabaseGUI.main();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                LoginGUI.main();
            }
        });
    }

    private void createUIComponents() {
        usersButton = new JButton();
        databaseButton = new JButton("dbbutton");
        try {
            Image img = ImageIO.read(new FileInputStream("data/userwhite.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image dtbImg = ImageIO.read(new FileInputStream("data/dbicon.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            usersButton.setIcon(new ImageIcon(img));
            databaseButton.setIcon(new ImageIcon(dtbImg));
        } catch (Exception ignored) {
        }

        // Make the rest of the button invisible
        usersButton.setBorder(null);
        usersButton.setBorderPainted(false);
        usersButton.setMargin(new Insets(0, 0, 0, 0));
        usersButton.setContentAreaFilled(false);
        usersButton.setFocusPainted(false);

        databaseButton.setBorder(null);
        databaseButton.setBorderPainted(false);
        databaseButton.setMargin(new Insets(0, 0, 0, 0));
        databaseButton.setContentAreaFilled(false);
        databaseButton.setFocusPainted(false);
    }
}
