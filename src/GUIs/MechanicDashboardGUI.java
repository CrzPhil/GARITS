package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class MechanicDashboardGUI extends JFrame{
    private JPanel main;
    private JButton logOutButton;
    private JButton jobsButton;
    private static MechanicDashboardGUI j = new MechanicDashboardGUI();

    public MechanicDashboardGUI() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                LoginGUI.main();
            }
        });
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
    }

    public static void main(){
        j.setContentPane(new MechanicDashboardGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Mechanic Dashboard");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        jobsButton  = new JButton();
        try {
            Image jImg = FindImages.getImageDisplay("job").getScaledInstance(175, 175, Image.SCALE_DEFAULT);
            jobsButton.setIcon(new ImageIcon(jImg));
        } catch (Exception ignored) {}
        jobsButton.setBorder(null);
        jobsButton.setBorderPainted(false);
        jobsButton.setMargin(new Insets(0, 0, 0, 0));
        jobsButton.setContentAreaFilled(false);
        jobsButton.setFocusPainted(false);
        }
    }

