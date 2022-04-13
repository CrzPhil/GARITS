import Customers.Customer;
import Customers.Customer_Controller;
import Customers.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditVehicleGUI extends JFrame {
    private JPanel Main;
    private JTextField regNoField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField engSerialField;
    private JTextField chassisNoField;
    private JTextField colourField;
    private JTextField motDateField;
    private JButton returnButton;
    private JButton saveChangesButton;
    private JButton deleteButton;
    private static final EditVehicleGUI j = new EditVehicleGUI();
    private Vehicle vehicle;

    public EditVehicleGUI() {}

    public EditVehicleGUI(Customer customer, Vehicle vehicle) {
        this.vehicle = vehicle;
        // The only field not rendering text is this for some reason.
        regNoField.setText(vehicle.getRegistrationNumber());

        returnButton.addActionListener(new ActionListener() {
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
                if( (d == '¬') || (d == '`') || (d == '!') || (d == '"') || (d == '£') || (d == 'F') || (d == '$') || (d == '%') || (d == '^') || (d == '&') || (d == '*') || (d == '(') || (d == ')') ||  (d == '-') ||(d == '_') || (d == '=') || (d == '+') ||  (d == '[') || (d == '{') || (d == ']') || (d == '}') || (d == ';') || (d == ':') || (d == '@') || (d == '#') || (d == '~') || (d == '|') || (d == ',') || (d == '<') || (d == '.') || (d == '>') ||(d == '/') || (d == 'I') || (d == 'i') || (d == 'O') || (d == 'o') || (d == 'z') || (d == 'Z') || (d== '?')){
                    f.consume();
                    JOptionPane.showMessageDialog(null, "Invalid Registration Character Used!");
                }
                if( (regNoField.getText().length() > 8)){
                    f.consume();
                    JOptionPane.showMessageDialog(null, "Registration Number Too Long!");
                }
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to save these changes?", "Confirm changes",JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Customer_Controller controller = new Customer_Controller();
                    if (controller.updateVehicle(vehicle.getRegistrationNumber(),
                            regNoField.getText(),
                            makeField.getText(),
                            modelField.getText(),
                            engSerialField.getText(),
                            chassisNoField.getText(),
                            colourField.getText(),
                            motDateField.getText(),
                            customer.getCustomerID())) {
                        j.dispose();
                        JOptionPane.showMessageDialog(null, "Successfully updated vehicle.");
                        ViewCustomerGUI.main(customer);
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong.\nIs your date formated properly? (YYYY-MM-DD)");
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Vehicle?", "Confirm deletion",JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Customer_Controller controller = new Customer_Controller();
                    if (controller.deleteVehicle(vehicle.getRegistrationNumber())) {
                        j.dispose();
                        JOptionPane.showMessageDialog(null, "Deletion successful.");
                        ViewCustomerGUI.main(customer);
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong. Contact your administrator.");
                    }
                }
            }
        });
    }

    public static void main(Customer customer, Vehicle vehicle) {
        j.setContentPane(new EditVehicleGUI(customer, vehicle).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("View Customer Account");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 600));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        if (vehicle != null) {
            regNoField = new JTextField(vehicle.getRegistrationNumber());
            makeField = new JTextField(vehicle.getMake());
            modelField = new JTextField(vehicle.getModel());
            engSerialField = new JTextField(vehicle.getEngSerial());
            chassisNoField = new JTextField(vehicle.getChassisNumber());
            colourField = new JTextField(vehicle.getColour());
            motDateField = new JTextField(vehicle.getMotDate());
        } else {
            regNoField = new JTextField();
            makeField = new JTextField();
            modelField = new JTextField();
            engSerialField = new JTextField();
            chassisNoField = new JTextField();
            colourField = new JTextField();
            motDateField = new JTextField();
        }
    }
}
