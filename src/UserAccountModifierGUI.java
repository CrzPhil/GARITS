import Accounts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UserAccountModifierGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField usernameField;
    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField roleField;
    private JTextField rateField;
    private JTextField emailField;
    private JButton saveChangesButton;
    private JButton changePasswordButton;
    private JLabel titleLabel;
    private JButton deleteAccountButton;
    private JLabel userIDTitle;
    private User account;
    private static UserAccountModifierGUI j = new UserAccountModifierGUI();

    public UserAccountModifierGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountMenuGUI.main();
            }
        });
    }

    public UserAccountModifierGUI(User account) {
        this.account = account;
        userIDTitle.setText(String.valueOf(this.account.getUserID()));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountMenuGUI.main();
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to make these changes?", "Confirm changes", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Accounts_Controller controller = new Accounts_Controller();

                    // Only mechanics and forepersons have rates, others will be NULL
                    String rate;
                    if (rateField.getText().equals("")) {
                        rate = "NULL";
                    } else {
                        rate = rateField.getText();
                    }

                    if (controller.modifyAccount(
                            fnameField.getText(),
                            lnameField.getText(),
                            usernameField.getText(),
                            roleField.getText(),
                            rate,
                            emailField.getText(),
                            account.getUserID())) {
                        JOptionPane.showMessageDialog(null, "Changes made successfully.");
                        j.dispose();
                        UserAccountMenuGUI.main();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!\nDouble check your details.");
                    }
                }
            }
        });
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Confirm account deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (deleteAccount(account)) {
                        JOptionPane.showMessageDialog(null, "Account deleted successfully.");
                        j.dispose();
                        UserAccountMenuGUI.main();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!");
                    }
                }
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField pass = new JPasswordField();
                JPasswordField confirm = new JPasswordField();
                Object[] message = {
                        "New Password:", pass,
                        "Confirm Password:", confirm
                };

                // Popup for new password
                int option = JOptionPane.showConfirmDialog(null, message, "Change Password", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    // If passwords are the same
                    if (Arrays.equals(pass.getPassword(), confirm.getPassword())) {
                        Accounts_Controller controller = new Accounts_Controller();

                        // Password REGEX check
                        String password = new String(pass.getPassword());
                        if (!controller.simplePasswordCheck(password)) {
                            JOptionPane.showMessageDialog(null, "Password has to be at least two characters with one uppercase letter.");
                        } else {
                            // Update password in DB
                            SQL_UserHelper helper = new SQL_UserHelper();
                            if (helper.updatePassword(account.getUserID(), pass.getPassword())) {
                                JOptionPane.showMessageDialog(null, "Password changed successfully.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Passwords have to match.");
                    }
                }

            }
        });
    }

    private boolean deleteAccount(User account) {
        Accounts_Controller controller = new Accounts_Controller();
        return controller.removeAccount(account.getUserID());
    }

    public static void main(User account) {
        j.setContentPane(new UserAccountModifierGUI(account).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Making Account Changes");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        if (this.account != null) {
            userIDTitle = new JLabel(String.valueOf(this.account.getUserID()));
            usernameField = new JTextField(this.account.getUsername());
            // TODO: Improve split into fname and lname
            fnameField = new JTextField(this.account.getName().split(" ")[0]);
            lnameField = new JTextField(this.account.getName().split(" ")[1]);
            roleField = new JTextField(this.account.getInstanceClass());
            rateField = new JTextField();
            if (this.account instanceof Mechanic) {
                rateField.setText(String.valueOf(((Mechanic) this.account).getHourlyRate()));
            } else if (this.account instanceof ForePerson) {
                rateField.setText(String.valueOf(((ForePerson) this.account).getHourlyRate()));
            } else {
                rateField = new JTextField();
            }
            emailField = new JTextField(this.account.getEmail());
        } else {
            userIDTitle = new JLabel();
            usernameField = new JTextField();
            fnameField = new JTextField();
            lnameField = new JTextField();
            roleField = new JTextField();
            rateField = new JTextField();
            emailField = new JTextField();
        }
    }
}
