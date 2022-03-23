import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    public JPanel Main;
    private JPasswordField txtPassword;
    private JTextField txtUsername;
    private JButton enterButton;
    private JButton administratorAccessButton;
    private static LoginGUI j = new LoginGUI();

    public LoginGUI() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());
                j.dispose();
                MainMenuGUI.main();

            }
        });

    }
    public static void main(){

        j.setContentPane(new LoginGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Please Enter Credentials.");
        j.setPreferredSize(new Dimension(1280, 720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
