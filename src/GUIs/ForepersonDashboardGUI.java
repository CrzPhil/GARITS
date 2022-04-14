package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class ForepersonDashboardGUI extends JFrame {
    private JPanel main;
    private JButton logOutButton;
    private JButton createJobButton;
    private JButton mechanicButton;
    private static ForepersonDashboardGUI j = new ForepersonDashboardGUI();


    public ForepersonDashboardGUI() {
        createJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CreateJobGUI.main();
            }
        });
        mechanicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
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

    public static void main() {
        j.setContentPane(new ForepersonDashboardGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Foreperson Dashboard");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);

    }

    private void createUIComponents() {
        createJobButton = new JButton();
        mechanicButton = new JButton();
        try {
            Image jImg = FindImages.getImageDisplay("plus").getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            Image mImg = FindImages.getImageDisplay("mechanic").getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            createJobButton.setIcon(new ImageIcon(jImg));
            mechanicButton.setIcon(new ImageIcon(mImg));
        } catch (Exception ignored) {}

        createJobButton.setBorder(null);
        createJobButton.setBorderPainted(false);
        createJobButton.setMargin(new Insets(0, 0, 0, 0));
        createJobButton.setContentAreaFilled(false);
        createJobButton.setFocusPainted(false);

        mechanicButton.setBorder(null);
        mechanicButton.setBorderPainted(false);
        mechanicButton.setMargin(new Insets(0, 0, 0, 0));
        mechanicButton.setContentAreaFilled(false);
        mechanicButton.setFocusPainted(false);
    }
}
