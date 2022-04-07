import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame{
    private JPanel Main;
    private JButton jobsButton;
    private JButton generateReportButton;
    private JButton sparePartsCatalogueButton;
    private JButton accountManagementButton;
    private JButton logOutButton;
    private JLabel ImageLogo;
    private JButton customerAccountManagementButton;
    private static MainMenuGUI j = new MainMenuGUI();

    public MainMenuGUI() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                LoginGUI.main();
            }
        });
        sparePartsCatalogueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ItemSearchGUI.main();
            }
        });
        accountManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                AdminLoginGUI.main();
            }
        });
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobsMenuGUI.main();
            }
        });
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ReportsMenuGUI.main();
            }
        });
        customerAccountManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                FranchiseeLoginGUI.main();
            }
        });
    }

    public static void main(){
        j.setContentPane(new MainMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Main Menu");
        j.setPreferredSize(new Dimension(1280,720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        ImageLogo = new JLabel(new ImageIcon("logo.png"));
    }
}
