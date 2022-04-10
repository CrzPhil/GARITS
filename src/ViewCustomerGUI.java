import Customers.Customer;
import Customers.Customer_Controller;
import Customers.Vehicle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
    private Vehicle selectedVehicle;

    public ViewCustomerGUI() {

    }

    public ViewCustomerGUI(Customer selectedCustomer) {
        this.currentCustomer = selectedCustomer;
        customerIDLabel.setText(String.valueOf(currentCustomer.getCustomerID()));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountMenuGUI.main();
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmation pop-up
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to make these changes?",
                        "Confirm changes",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Customer_Controller controller = new Customer_Controller();
                    // Verify input
                    if (controller.digestInfo(telephoneField.getText(),
                            emailField.getText(),
                            discountField.getText(),
                            faxField.getText())) {

                        // TODO: Once discount plans work (valued customer etc) implement updating in Database for the JBox
                        if (controller.updateCustomer(nameField.getText().split("\\s+")[0],
                                nameField.getText().split("\\s+")[1],
                                addressField.getText(),
                                telephoneField.getText(),
                                emailField.getText(),
                                faxField.getText(),
                                Integer.parseInt(discountField.getText()),
                                currentCustomer.getCustomerID())) {
                            JOptionPane.showMessageDialog(null, "Customer Account updated successfully.");
                            j.dispose();
                            CustomerAccountMenuGUI.main();
                        } else {
                            JOptionPane.showMessageDialog(null, "Something went wrong.\n Contact your administrator.");
                        }

                    }
                }
            }
        });
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                j.dispose();
                CreateVehicleGUI.main(currentCustomer);
            }
        });

        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer_Controller controller = new Customer_Controller();
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Confirm account deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (controller.deleteCustomer(currentCustomer)) {
                        JOptionPane.showMessageDialog(null, "Account deleted successfully.");
                        j.dispose();
                        CustomerAccountMenuGUI.main();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!");
                    }
                }
            }
        });
        editVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedVehicle != null) {
                    j.dispose();
                    EditVehicleGUI.main(currentCustomer, selectedVehicle);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a vehicle first!");
                }
            }
        });
    }

    public static void main(Customer customer){
        j.setContentPane(new ViewCustomerGUI(customer).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("View Customer Account");
        j.setPreferredSize(new Dimension(800, 600));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createVehicleList() {
        Customer_Controller controller = new Customer_Controller();
        Vehicle[] vehicles = controller.getVehicles(this.currentCustomer);
        DefaultListModel<Vehicle> vehicleModel = new DefaultListModel<Vehicle>();

        // Add customers to list
        for (Vehicle vehicle : vehicles) {
            vehicleModel.addElement(vehicle);
        }

        vehicleList = new JList<>(vehicleModel);
        vehicleList.setFixedCellHeight(80);
        vehicleList.setFont(new Font("monospaced", Font.PLAIN, 18));
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        vehicleList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedVehicle = (Vehicle) vehicleList.getSelectedValue();
                }
            }
        });
    }

    private void createUIComponents() {
        if (currentCustomer != null) {
            customerIDLabel = new JLabel(String.valueOf(currentCustomer.getCustomerID()));
            nameField = new JTextField(currentCustomer.getName());
            addressField = new JTextField(currentCustomer.getAddress());
            telephoneField = new JTextField(currentCustomer.getTelephone());
            emailField = new JTextField(currentCustomer.getEmail());
            faxField = new JTextField(currentCustomer.getFax());
            discountField = new JTextField(currentCustomer.getDiscount());

            // Because we do everything for a nice UX :)
            // Customer's discount plan should already be selected, and the other two should be options
            String otherOption;
            String otherotherOption;
            if (Objects.equals(currentCustomer.getDiscountPlan(), Customer.plans[0])) {
                otherOption = Customer.plans[1];
                otherotherOption = Customer.plans[2];
            } else if (Objects.equals(currentCustomer.getDiscountPlan(), Customer.plans[1])) {
                otherOption = Customer.plans[0];
                otherotherOption = Customer.plans[2];
            } else {
                otherOption = Customer.plans[0];
                otherotherOption = Customer.plans[1];
            }

            discountPlan = new JComboBox<String>(new String[]{currentCustomer.getDiscountPlan(), otherOption, otherotherOption});

            createVehicleList();
        } else {
            customerIDLabel = new JLabel();
            nameField = new JTextField();
            addressField = new JTextField();
            telephoneField = new JTextField();
            emailField = new JTextField();
            faxField = new JTextField();
            discountField = new JTextField();
            discountPlan = new JComboBox();
            vehicleList = new JList();
        }
    }
}
