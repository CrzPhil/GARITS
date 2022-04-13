import Customers.Customer;
import Customers.Customer_Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountMenuGUI extends JFrame{
    private JPanel Main;
    private JList customerList;
    private JButton inspectCustomerButton;
    private JButton returnButton;
    private JButton addCustomerButton;
    private static final CustomerAccountMenuGUI j = new CustomerAccountMenuGUI();
    private Customer selectedCustomer;

    public CustomerAccountMenuGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   j.dispose();
                if (LoginGUI.access == 'C') {
                    FranchiseeDashboardGUI.main();
                } else if (LoginGUI.access == 'R'){
                    ReceptionistDashboardGUI.main();
                } else {
                    MainMenuGUI.main();
                }
            }
        });
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountCreationGUI.main();
            }
        });
        inspectCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCustomer != null ) {
                    j.dispose();
                    ViewCustomerGUI.main(selectedCustomer);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a customer account first!");
                }
            }
        });
    }


    public static void main(){
        j.setContentPane(new CustomerAccountMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Customer Account Library");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,600));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        Customer_Controller controller = new Customer_Controller();
        Customer[] customers = controller.getCustomers();

        DefaultListModel<Customer> customerModel = new DefaultListModel<Customer>();

        // Add customers to list
        for (Customer customer : customers) {
            customerModel.addElement(customer);
        }

        // Specify jobList
        customerList = new JList<>(customerModel);
        customerList.setFixedCellHeight(80);
        customerList.setFont(new Font("monospaced", Font.PLAIN, 18));
        customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Listener for when an item is selected
        customerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedCustomer = (Customer) customerList.getSelectedValue();
                }
            }
        });
    }
}
