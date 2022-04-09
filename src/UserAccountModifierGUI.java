import Accounts.Accounts_Controller;
import Accounts.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    JOptionPane.showMessageDialog(null, "Changes made successfully.");
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
    }

    private boolean deleteAccount(User account) {
        Accounts_Controller controller = new Accounts_Controller();
        return controller.removeAccount(account.getUserID());
    }

    public static void main(User account){
        j.setContentPane(new UserAccountModifierGUI(account).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Making Account Changes");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        if (this.account != null) {
            usernameField = new JTextField(this.account.getUsername());
            // TODO: Improve split into fname and lname
            fnameField = new JTextField(this.account.getName().split(" ")[0]);
            lnameField = new JTextField(this.account.getName().split(" ")[1]);
            roleField = new JTextField("");
            rateField = new JTextField("");
            emailField = new JTextField(this.account.getEmail());
        } else {
            usernameField = new JTextField();
            fnameField = new JTextField();
            lnameField = new JTextField();
            roleField = new JTextField();
            rateField = new JTextField();
            emailField = new JTextField();
        }
    }
}
