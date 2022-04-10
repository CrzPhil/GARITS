import Accounts.Accounts_Controller;
import Accounts.SQL_UserHelper;
import Customers.Customer_Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountCreationGUI extends JFrame{
    private JPanel Main;
    private JLabel titleLabel;
    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField addressField;
    private JTextField telField;
    private JTextField discountField;
    private JTextField faxField;
    private JFormattedTextField mailField;
    private JButton createStaffAccountButton;
    private JButton returnButton;
    private static UserAccountCreationGUI j = new UserAccountCreationGUI();

    public CustomerAccountCreationGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountMenuGUI.main();
            }
        });

        // Create the actual account + verify password
        createStaffAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname = fnameField.getText();
                String lname = lnameField.getText();
                String mail = mailField.getText();
                String address = addressField.getText();
                String phone = telField.getText();
                String discount = discountField.getText();
                String fax = faxField.getText();

                Customer_Controller controller = new Customer_Controller();

                if (controller.digestInfo(phone, mail, discount, fax)) {
                    // Create account + add to database
                    controller.createCustomer(fname, lname, address, phone, mail, fax, Integer.parseInt(discount));

                    // Success popup
                    JOptionPane.showMessageDialog(null, "Account created successfully!");

                    j.dispose();
                    CustomerAccountMenuGUI.main();
                }
            }
        });
    }

    public static void main() {
        j.setContentPane(new CustomerAccountCreationGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Create Customer Account");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    // Checks for existing username in the Database
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
