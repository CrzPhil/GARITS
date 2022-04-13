package GUIs;

import Job.SQL_PartsHelper;
import Job.SparePart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSparePartGUI extends JFrame {
    private JPanel Main;
    private JTextField nameField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField stockField;
    private JTextField priceField;
    private JTextField thresholdField;
    private JButton returnButton;
    private JButton saveChangesButton;
    private JLabel codeIDLabel;

    private static EditSparePartGUI j = new EditSparePartGUI();
    private SparePart currentPart;

    public EditSparePartGUI() {
    }

    public EditSparePartGUI(SparePart currentPart) {
        this.currentPart = currentPart;
        codeIDLabel.setText(currentPart.getPartCode());

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchResultsGUI.main(currentPart.getType());
                j.dispose();
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get all the input
                String code = currentPart.getPartCode();
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
                    if (helper.updateSparePart(code, name, make, model, Integer.parseInt(year), Integer.parseInt(stock), Float.parseFloat(price), Integer.parseInt(threshold))) {
                        JOptionPane.showMessageDialog(null, "Spare Part edited successfully.");
                        helper.closeConnection();
                        j.dispose();
                        ItemSearchGUI.main();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong. Contact your administrator.");
                    }
                }
            }
        });
    }

    private boolean validateInput(String code, String name,String make,String model,String year,String stock,String price,String threshold) {
        SQL_PartsHelper helper = new SQL_PartsHelper();

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


    public static void main(SparePart currentPart) {
        j.setContentPane(new EditSparePartGUI(currentPart).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Edit Spare Part");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        if (currentPart != null) {
            codeIDLabel = new JLabel(currentPart.getPartCode());
            nameField = new JTextField(currentPart.getName());
            makeField = new JTextField(currentPart.getManufacturer());
            modelField = new JTextField(currentPart.getType());
            yearField = new JTextField(String.valueOf(currentPart.getYear()));
            stockField = new JTextField(String.valueOf(currentPart.getStock()));
            priceField = new JTextField(String.valueOf(currentPart.getPrice()));
            thresholdField = new JTextField(String.valueOf(currentPart.getThreshold()));
        } else {
            codeIDLabel = new JLabel();
            nameField = new JTextField();
            makeField = new JTextField();
            modelField = new JTextField();
            yearField = new JTextField();
            stockField = new JTextField();
            priceField = new JTextField();
            thresholdField = new JTextField();
        }
    }
}
