import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginGUI extends JFrame{
    private JPanel Main;
    private JButton enterButton;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton returnButton;
    private static AdminLoginGUI j = new AdminLoginGUI();

    public AdminLoginGUI() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String password = String.valueOf(txtPassword.getPassword());
                if (password.equals("adminpass")) {
                    j.dispose();
                    UserAccountMenuGUI.main();
                } else {
                    // Error pop up for wrong password
                    JOptionPane.showMessageDialog(null, "Wrong Password!");
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                MainMenuGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new AdminLoginGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Please Enter Credentials.");
        j.setPreferredSize(new Dimension(1280, 720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);

    }

}
