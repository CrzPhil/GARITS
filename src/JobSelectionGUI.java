import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

public class JobSelectionGUI extends JFrame{
    private JPanel Main;
    private JLabel titleLabel;
    private JLabel incomLabel;
    private JButton incompButton;
    private JLabel CompLabel;
    private JButton CompButton;
    private JButton returnButton;
    private JButton createButton;
    public static JobSelectionGUI j = new JobSelectionGUI();

    public static void main() {
        j.setContentPane(new JobSelectionGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Job Selection");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    public JobSelectionGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                if (LoginGUI.access == 'C') {
                    FranchiseeDashboardGUI.main();
                } else if (LoginGUI.access == 'R'){
                    ReceptionistDashboardGUI.main();
                } else {
                    MainMenuGUI.main();
                }
            }
        });
        incompButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
        CompButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewCompletedJobsGUI.main();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CreateJobGUI.main();
            }
        });
    }

    private void createUIComponents() {
        incompButton = new JButton();
        CompButton = new JButton();
        createButton = new JButton();
        try {
            Image img = ImageIO.read(new FileInputStream("data/loading.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image tImg = ImageIO.read(new FileInputStream("data/tick3.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image bImg = ImageIO.read(new FileInputStream("data/plus.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            incompButton.setIcon(new ImageIcon(img));
            CompButton.setIcon(new ImageIcon(tImg));
            createButton.setIcon(new ImageIcon(bImg));
        } catch (Exception ignored) {
        }

        // Make the rest of the button invisible
        incompButton.setBorder(null);
        incompButton.setBorderPainted(false);
        incompButton.setMargin(new Insets(0, 0, 0, 0));
        incompButton.setContentAreaFilled(false);
        incompButton.setFocusPainted(false);

        CompButton.setBorder(null);
        CompButton.setBorderPainted(false);
        CompButton.setMargin(new Insets(0, 0, 0, 0));
        CompButton.setContentAreaFilled(false);
        CompButton.setFocusPainted(false);

        createButton.setBorder(null);
        createButton.setBorderPainted(false);
        createButton.setMargin(new Insets(0, 0, 0, 0));
        createButton.setContentAreaFilled(false);
        createButton.setFocusPainted(false);
    }
}
