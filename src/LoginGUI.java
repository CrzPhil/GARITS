import Accounts.HashClass;
import Accounts.SQL_UserHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

                // Credential checker verifies if username corresponds to hashed version of the password
                if (checkCredentials(username, password)) {
                    String role = getRole(username);
                    j.dispose();

                    // Different roles have access to different parts of GARITS
                    switch (role) {
                        case "Administrator":
                            UserAccountMenuGUI.main();
                            break;
                        default:
                            MainMenuGUI.main();
                            break;
                    }
                } else {
                    // Error pop up for wrong password
                    JOptionPane.showMessageDialog(null, "Wrong Password!");
                }

            }
        });

    }

    // Helper function to check credentials
    private boolean checkCredentials(String username, String password) {
        SQL_UserHelper helper = new SQL_UserHelper();
        HashClass hasher = new HashClass();
        boolean login = helper.compareCredentials(username, hasher.stringtosha256(password));

        // Cannot instantly return from helper function, as helper has to be closed first.
        helper.closeConnection();

        return login;
    }

    // Get the role of a given username
    private String getRole(String username) {
        SQL_UserHelper helper = new SQL_UserHelper();
        String role = helper.getRole(username);

        // Cannot instantly return from helper function, as helper has to be closed first.
        helper.closeConnection();

        return role;
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
