package GUIs;

import Job.SQL_PartsHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSparePartGUI extends JFrame {
    private JPanel Main;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField stockField;
    private JTextField priceField;
    private JTextField thresholdField;
    private JLabel createPartTitle;
    private JButton createButton;
    private JButton returnButton;
    private static CreateSparePartGUI j = new CreateSparePartGUI();

    public CreateSparePartGUI() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get all the input
                String code = codeField.getText();
                String name = nameField.getText();
                String make = makeField.getText();
                String model = modelField.getText();
                String year = yearField.getText();
                String stock = stockField.getText();
                String price = priceField.getText();
                String threshold = thresholdField.getText();

                // Validate input & create Spare Part
                if (validateInput(code, name, make, model, year, stock, price, threshold)) {
                    SQL_PartsHelper helper = new SQL_PartsHelper();
                    if (helper.createSparePart(code, name, make, model, Integer.parseInt(year), Integer.parseInt(stock), (float) (Float.parseFloat(price)+(Float.parseFloat(price)*0.3)), Integer.parseInt(threshold))) {
                        JOptionPane.showMessageDialog(null, "Spare Part created successfully.");
                        helper.closeConnection();
                        j.dispose();
                        ItemSearchGUI.main();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong. Contact your administrator.");
                    }
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ItemSearchGUI.main();
            }
        });
    }

    private boolean validateInput(String code, String name,String make,String model,String year,String stock,String price,String threshold) {
        SQL_PartsHelper helper = new SQL_PartsHelper();
        if (code.equals("")) {
            JOptionPane.showMessageDialog(null, "Code cannot be empty!");
            return false;
        }
        // If code already exists, show error.
        if (helper.getCode(code)) {
            JOptionPane.showMessageDialog(null, "Part with that code already exists!");
            return false;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!");
            return false;
        }
        if (make.equals("")) {
            JOptionPane.showMessageDialog(null, "Make cannot be empty!");
            return false;
        }
        if (model.equals("")) {
            JOptionPane.showMessageDialog(null, "Model cannot be empty!");
            return false;
        }

        // Check integers & floats
        try {
            Integer.parseInt(year);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Year is invalid.");
            return false;
        }
        try {
            if (Integer.parseInt(stock) < 0) {
                JOptionPane.showMessageDialog(null, "Stock cannot be a negative number.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Stock is invalid.");
            return false;
        }
        try {
            if (Float.parseFloat(price) <= 0) {
                JOptionPane.showMessageDialog(null, "Price cannot be negative or 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Price is invalid.");
            return false;
        }
        try {
            if (Integer.parseInt(threshold) < 0) {
                JOptionPane.showMessageDialog(null, "Threshold cannot be less than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Threshold is invalid.");
            return false;
        }
        return true;
    }

    public static void main(){
        j.setContentPane(new CreateSparePartGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Spare Part Creation Menu");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
