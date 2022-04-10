import Customers.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCustomerGUI extends JFrame {
    private JPanel Main;
    private JList vehicleList;
    private JLabel customerIDLabel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField faxField;
    private JTextField discountField;
    private JButton returnButton;
    private JButton deleteCustomerButton;
    private JButton saveChangesButton;
    private JButton addVehicleButton;
    private JComboBox discountPlan;
    private JButton editVehicleButton;
    private static ViewCustomerGUI j = new ViewCustomerGUI();
    private Customer currentCustomer;

    public ViewCustomerGUI() {}

    public ViewCustomerGUI(Customer selectedCustomer) {
        this.currentCustomer = selectedCustomer;
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountMenuGUI.main();
            }
        });
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CreateVehicleGUI.main(currentCustomer);
            }
        });
    }

    public static void main(){
        j.setContentPane(new ViewCustomerGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("View Customer Account");
        j.setPreferredSize(new Dimension(800, 600));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
