import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class ReceptionistDashboardGUI extends JFrame{
    private JPanel main;
    private JButton logOutButton;
    private JButton stockButton;
    private JButton jobsButton;
    private JButton customerButton;
    private static ReceptionistDashboardGUI j = new ReceptionistDashboardGUI();

    public ReceptionistDashboardGUI() {
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
        stockButton.addActionListener(new ActionListener() {
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
        j.setContentPane(new ReceptionistDashboardGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Receptionist Dashboard");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        jobsButton  = new JButton();
        stockButton = new JButton();
        customerButton = new JButton();
        try {
            Image jImg = ImageIO.read(new FileInputStream("data/job.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            Image sImg = ImageIO.read(new FileInputStream("data/part.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            Image cImg = ImageIO.read(new FileInputStream("data/userwhite.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            jobsButton.setIcon(new ImageIcon(jImg));
            stockButton.setIcon(new ImageIcon(sImg));
            customerButton.setIcon(new ImageIcon(cImg));
        } catch (Exception ignored) {}

        jobsButton.setBorder(null);
        jobsButton.setBorderPainted(false);
        jobsButton.setMargin(new Insets(0, 0, 0, 0));
        jobsButton.setContentAreaFilled(false);
        jobsButton.setFocusPainted(false);

        stockButton.setBorder(null);
        stockButton.setBorderPainted(false);
        stockButton.setMargin(new Insets(0, 0, 0, 0));
        stockButton.setContentAreaFilled(false);
        stockButton.setFocusPainted(false);

        customerButton.setBorder(null);
        customerButton.setBorderPainted(false);
        customerButton.setMargin(new Insets(0, 0, 0, 0));
        customerButton.setContentAreaFilled(false);
        customerButton.setFocusPainted(false);
    }
}
