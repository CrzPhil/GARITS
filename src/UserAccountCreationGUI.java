import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAccountCreationGUI extends JFrame {
    private JPanel Main;
    private JLabel titleLabel;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JFormattedTextField formattedTextField4;
    private JButton createStaffAccountButton;
    private JButton returnButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private static UserAccountCreationGUI j = new UserAccountCreationGUI();

    public UserAccountCreationGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountMenuGUI.main();
            }
        });
    }

    public static void main() {
        j.setContentPane(new UserAccountCreationGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Create Staff Account");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
