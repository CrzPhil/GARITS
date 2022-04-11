import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class JobSelectionGUI extends JFrame{
    private JPanel Main;
    private JLabel titleLabel;
    private JLabel incomLabel;
    private JButton incompButton;
    private JLabel CompLabel;
    private JButton CompButton;
    private JButton logOutButton;
    private JButton returnButton;
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
                MainMenuGUI.main();
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
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                LoginGUI.main();
            }
        });
    }

    private void createUIComponents() {
        incompButton = new JButton();
        CompButton = new JButton();
        try {
            Image img = ImageIO.read(new FileInputStream("data/inProg.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image tImg = ImageIO.read(new FileInputStream("data/tick3.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            incompButton.setIcon(new ImageIcon(img));
            CompButton.setIcon(new ImageIcon(tImg));
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
    }
}
