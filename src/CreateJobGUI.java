import Job.Job_Controller;
import Job.SQL_JobHelper;
import Job.SparePart;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateJobGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField priceField;
    private JTextField dateField;
    private JTextField typeField;
    private JTextField durationField;
    private JTextField partsField;
    private JTextField motField;
    private JTextField mileageField;
    private JTextField regNoField;
    private JButton finishButton;
    private JTextArea detailsField;
    private JComboBox jobTypeBox;
    private JDateChooser jDateChooser;
    private JButton addPartButton;
    private JButton deletePartButton;
    private JList partList;
    private JComboBox partSelectBox;
    private static CreateJobGUI j = new CreateJobGUI();
    private DefaultListModel<SparePart> partModel;

    public CreateJobGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobSelectionGUI.main();
            }
        });

        mileageField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                    JOptionPane.showMessageDialog(null, "Numbers Only!");
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!checkNumber(priceField.getText())) {
                    JOptionPane.showMessageDialog(null, "Price has to be in the format: 12.34");
                } else if (!checkNumber(durationField.getText())) {
                    JOptionPane.showMessageDialog(null, "Duration is stored in hours, using the format: 1.5");
                } else {
                    // TODO: Check if records are empty
                    SQL_JobHelper sqlJob = new SQL_JobHelper();
                    String jobType = (String) jobTypeBox.getSelectedItem();
                    float duration = Float.parseFloat(durationField.getText());
                    String dates = String.valueOf(jDateChooser.getDate());
                    String parts = partsField.getText();
                    String motNo = motField.getText();
                    int mileage = Integer.parseInt(mileageField.getText());
                    float price = Float.parseFloat(priceField.getText());
                    String additionalInfo = detailsField.getText();

                    sqlJob.sendData(jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, "Incomplete");
                    j.dispose();
                    JobSelectionGUI.main();
                }
            }
        });
        // TODO: Make this more readable
        addPartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partSelectBox.getSelectedItem() != null) {
                    if (((SparePart) partSelectBox.getSelectedItem()).getStock() >= 1) {
                        // Add Item to JList
                        partModel.addElement((SparePart) partSelectBox.getSelectedItem());
                        // Update part Object
                        ((SparePart) partSelectBox.getSelectedItem()).setStock(((SparePart) partSelectBox.getSelectedItem()).getStock() - 1);
                        // Update database
                        Job_Controller controller = new Job_Controller();
                        controller.updateStock(((SparePart) partSelectBox.getSelectedItem()).getStock(), ((SparePart)  partSelectBox.getSelectedItem()).getPartID());
                    }
                    // If Stock is less than one
                    else {
                        JOptionPane.showMessageDialog(null, "This item's stock is depleted.");
                    }
                }
            }
        });
        deletePartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partList.getSelectedValue() != null) {
                    ((SparePart) partList.getSelectedValue()).setStock(((SparePart) partList.getSelectedValue()).getStock() + 1);

                    // Update database
                    Job_Controller controller = new Job_Controller();
                    controller.updateStock(((SparePart) partList.getSelectedValue()).getStock(), ((SparePart)  partList.getSelectedValue()).getPartID());

                    // Remove item from list last, so that selectedValue doesn't move
                    partModel.removeElement(partList.getSelectedValue());

                }
            }
        });
    }

    // Check if things like price and duration are floats
    private boolean checkNumber(String input) {
        String numberRegex = "^(?=.)([+-]?([0-9]*)(\\.([0-9]+))?)$";
        Pattern p = Pattern.compile(numberRegex);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    public static void main(){
        j.setContentPane(new CreateJobGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Job Creation Menu");
        j.setPreferredSize(new Dimension(1280,720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
    private void createUIComponents(){
        Job_Controller controller = new Job_Controller();
        jDateChooser = new JDateChooser();

        // Configure List of added parts
        this.partModel = new DefaultListModel<SparePart>();
        partList = new JList<>(partModel);
        partList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Dropdown of possible parts to add to job
        // TODO: filter this to become vehicle-specific (manufacturer/model)
        partSelectBox = new JComboBox<>(controller.getAllParts());
}
}

