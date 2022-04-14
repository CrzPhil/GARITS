package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class FranchiseeDashboardGUI extends JFrame{
    private JPanel main;
    private JButton logOutButton;
    private JButton jobsButton;
    private JButton reportsButton;
    private JButton spareButton;
    private JButton customerButton;
    private static FranchiseeDashboardGUI j = new FranchiseeDashboardGUI();

    public FranchiseeDashboardGUI() {
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
                JobSelectionGUI.main();

            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ReportsMenuGUI.main();
            }
        });
        spareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ItemSearchGUI.main();
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountMenuGUI.main();
            }
        });
    }

    public static void main(){
        j.setContentPane(new FranchiseeDashboardGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Franchisee Dashboard");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);

        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        jobsButton = new JButton();
        reportsButton = new JButton();
        spareButton = new JButton();
        customerButton = new JButton();
        try {
            Image cImg = FindImages.getImageDisplay("userwhite").getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image jImg = FindImages.getImageDisplay("job").getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image rImg = FindImages.getImageDisplay("report").getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image sImg = FindImages.getImageDisplay("part").getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            jobsButton.setIcon(new ImageIcon(jImg));
            reportsButton.setIcon(new ImageIcon(rImg));
            spareButton.setIcon(new ImageIcon(sImg));
            customerButton.setIcon(new ImageIcon(cImg));
        } catch (Exception ignored) {
        }

        // Make the rest of the button invisible
        jobsButton.setBorder(null);
        jobsButton.setBorderPainted(false);
        jobsButton.setMargin(new Insets(0, 0, 0, 0));
        jobsButton.setContentAreaFilled(false);
        jobsButton.setFocusPainted(false);

        reportsButton.setBorder(null);
        reportsButton.setBorderPainted(false);
        reportsButton.setMargin(new Insets(0, 0, 0, 0));
        reportsButton.setContentAreaFilled(false);
        reportsButton.setFocusPainted(false);

        spareButton.setBorder(null);
        spareButton.setBorderPainted(false);
        spareButton.setMargin(new Insets(0, 0, 0, 0));
        spareButton.setContentAreaFilled(false);
        spareButton.setFocusPainted(false);

        customerButton.setBorder(null);
        customerButton.setBorderPainted(false);
        customerButton.setMargin(new Insets(0, 0, 0, 0));
        customerButton.setContentAreaFilled(false);
        customerButton.setFocusPainted(false);
    }
}
