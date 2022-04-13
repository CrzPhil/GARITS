import Job.SparePart;

import javax.swing.*;
import java.awt.*;

public class EditSparePartGUI extends JFrame {
    private JPanel Main;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField stockField;
    private JTextField priceField;
    private JTextField thresholdField;
    private JButton returnButton;
    private JButton saveChangesButton;

    private static EditSparePartGUI j = new EditSparePartGUI();
    private SparePart currentPart;

    public EditSparePartGUI() {
    }

    public EditSparePartGUI(SparePart currentPart) {
        this.currentPart = currentPart;
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
            codeField = new JTextField(currentPart.getPartCode());
            nameField = new JTextField(currentPart.getName());
            makeField = new JTextField(currentPart.getManufacturer());
            modelField = new JTextField(currentPart.getType());
            yearField = new JTextField(currentPart.getYear());
            stockField = new JTextField(currentPart.getStock());
            priceField = new JTextField(String.valueOf(currentPart.getPrice()));
            thresholdField = new JTextField(currentPart.getThreshold());
        } else {
            codeField = new JTextField();
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
