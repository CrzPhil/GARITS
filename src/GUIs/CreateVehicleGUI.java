package GUIs;

import Customers.Customer;
import Customers.Customer_Controller;
import Customers.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateVehicleGUI extends JFrame {
    private JPanel Main;
    private JLabel titleLabel;
    private JLabel customerNameLabel;
    private JTextField regNoField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField engSerialField;
    private JTextField motDateField;
    private JTextField chassisNoField;
    private JTextField colourField;
    private JButton discardChangesButton;
    private JButton addVehicleButton;
    private JButton returnButton;
    private static CreateVehicleGUI j = new CreateVehicleGUI();
    private Customer customer;
    private final Customer_Controller controller = new Customer_Controller();

    public CreateVehicleGUI() {


    }

    public CreateVehicleGUI(Customer customer) {
        this.customer = customer;
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.vehicleExists(regNoField.getText())) {
                    JOptionPane.showMessageDialog(null, "A vehicle with that registration number already exists!");
                } else {
                    Vehicle newVehicle = new Vehicle(
                            regNoField.getText(),
                            makeField.getText(),
                            modelField.getText(),
                            engSerialField.getText(),
                            chassisNoField.getText(),
                            colourField.getText(),
                            motDateField.getText(),
                            customer);
                    if (controller.createVehicle(newVehicle)) {
                        j.dispose();
                        JOptionPane.showMessageDialog(null, "Vehicle created successfully.");
                        ViewCustomerGUI.main(customer);
                    } else {
                        JOptionPane.showMessageDialog(null, "Check your details again.\n" +
                                "Date has to be in the format DD/MM/YYYY");
                    }
                }
            }
        });
        discardChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewCustomerGUI.main(customer);

            }
        });

        regNoField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent f) {
                char d = f.getKeyChar();
                //all special characters, and letters not allowed on a reg plate
                if( (d == '¬') || (d == '`') || (d == '!') || (d == '"') || (d == '£') || (d == '$') || (d == '%') || (d == '^') || (d == '&') || (d == '*') || (d == '(') || (d == ')') ||  (d == '-') ||(d == '_') || (d == '=') || (d == '+') ||  (d == '[') || (d == '{') || (d == ']') || (d == '}') || (d == ';') || (d == ':') || (d == '@') || (d == '#') || (d == '~') || (d == '|') || (d == ',') || (d == '<') || (d == '.') || (d == '>') ||(d == '/') || (d == 'I') || (d == 'i') || (d == 'O') || (d == 'o') || (d == 'z') || (d == 'Z') || (d== '?')){
                    f.consume();
                    JOptionPane.showMessageDialog(null, "Invalid Registration Character Used!");
                }
                if( (regNoField.getText().length() > 8)){
                    f.consume();
                    JOptionPane.showMessageDialog(null, "Registration Number Too Long!");
                }
            }
        });
    }


    public static void main(Customer customer){
        j.setContentPane(new CreateVehicleGUI(customer).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Add Vehicle to Customer");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 600));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        if (customer != null) {
            customerNameLabel = new JLabel(customer.getName());
        } else {
            customerNameLabel = new JLabel();
        }
    }
}
