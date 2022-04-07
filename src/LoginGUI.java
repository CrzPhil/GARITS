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

    // TODO: Inspired by https://www.baeldung.com/sha-256-hashing-java
    private String byteToString(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder(2 * bytes.length);
        for (byte i : bytes) {
            String hex = Integer.toHexString(0xff & i);
            if (hex.length() == 1)
                hexStr.append('0');
            hexStr.append(hex);
        }
        return hexStr.toString();
    }

    // Passwords are sha-256 encoded in the database
    private String stringtosha256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return byteToString(encodedhash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper function to check credentials
    private boolean checkCredentials(String username, String password) {
        SQL_UserHelper helper = new SQL_UserHelper();
        boolean login = helper.compareCredentials(username, stringtosha256(password));

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
