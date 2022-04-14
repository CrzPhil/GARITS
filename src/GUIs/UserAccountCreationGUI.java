package GUIs;

import Accounts.Accounts_Controller;
import Accounts.SQL_UserHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAccountCreationGUI extends JFrame {
    private JPanel Main;
    private JLabel titleLabel;
    private JFormattedTextField rateField;
    private JFormattedTextField usernameField;
    private JFormattedTextField roleField;
    private JFormattedTextField emailField;
    private JButton createStaffAccountButton;
    private JButton returnButton;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JFormattedTextField firstNameField;
    private JFormattedTextField lastNameField;
    private static UserAccountCreationGUI j = new UserAccountCreationGUI();

    public UserAccountCreationGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountMenuGUI.main();
            }
        });

        // Create the actual account + verify password
        createStaffAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname = firstNameField.getText();
                String lname = lastNameField.getText();
                String rate = rateField.getText();
                String username = usernameField.getText();
                char[] pass = passwordField.getPassword();
                char[] confirmpass = confirmField.getPassword();
                String mail = emailField.getText();
                String role = roleField.getText();

                if (digestInfo(rate, username, pass, confirmpass, mail, role)) {
                    // Create account + add to database
                    Accounts_Controller controller = new Accounts_Controller();
                    controller.createUser(fname, lname, username, pass, role, mail, rate);

                    // Success popup
                    JOptionPane.showMessageDialog(null, "Account created successfully!");

                    j.dispose();
                    UserAccountMenuGUI.main();
                }
            }
        });
    }

    /**
     * Method to validate user input; mostly regex, one query to the database to check for the username
     * @param rate rate
     * @param username username
     * @param pass pass
     * @param confirmpass confirmpass
     * @param mail mail
     * @param role role
     * @return Whether input is valid
     */
    private boolean digestInfo(String rate, String username, char[] pass, char[] confirmpass, String mail, String role) {
        Accounts_Controller controller = new Accounts_Controller();

        if (!controller.validateName(firstNameField.getText())) {
            JOptionPane.showMessageDialog(null, "Please include first name.");
            return false;
        }

        if (!controller.validateName(lastNameField.getText())) {
            JOptionPane.showMessageDialog(null, "Please include last name.");
            return false;
        }

        if (!controller.validateUsername(username)) {
            JOptionPane.showMessageDialog(null, "Username has to start with a letter and be between 5 and 29 characters. No special characters allowed.");
            return false;
        }

        // Check if username is taken
        if (!checkForUsername(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists!");
            return false;
        }

        String password = new String(pass);
        if (!password.equals(new String(confirmpass))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return false;
        }

        // Password check disabled, as demo passwords are simple.
/*        if (!controller.validatePassword(password)) {
            JOptionPane.showMessageDialog(null, "Check that your password: \n" +
                    "    contains at least 8 characters and at most 20 characters.\n" +
                    "    contains at least one digit.\n" +
                    "    contains at least one upper case alphabet.\n" +
                    "    contains at least one lower case alphabet.\n" +
                    "    contains at least one special character which includes !@#$%&*()-+=^.\n" +
                    "    doesn’t contain any white space.");
            return false;
        }*/

        if (!controller.simplePasswordCheck(password)) {
            JOptionPane.showMessageDialog(null, "Password has to be at least two characters with one uppercase letter.");
            return false;
        }

        if (!controller.validateEmail(mail)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
            return false;
        }

        if (!controller.validateRole(role)) {
            JOptionPane.showMessageDialog(null, "Role does not exist.");
            return false;
        }

        if (!controller.validateRate(rate)) {
            JOptionPane.showMessageDialog(null, "Enter a valid rate.");
            return false;
        }

        return true;
    }

    public static void main() {
        j.setContentPane(new UserAccountCreationGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Create Staff Account");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    /**
     * Checks for existing username in the Database
     * @param username username
     * @return Whether username exists in DB
     */
    private boolean checkForUsername(String username) {
        SQL_UserHelper helper = new SQL_UserHelper();

        // If user already exists, a role will be found. Recycling methods here.
        if (helper.getStaff(username) != null) {
            return false;
        }

        helper.closeConnection();

        return true;
    }
}
