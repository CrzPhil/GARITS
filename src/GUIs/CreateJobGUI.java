package GUIs;

import Job.Job_Controller;
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
    private JTextField durationField;
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
        configureList();
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Update database
                Job_Controller controller = new Job_Controller();

                // Release items that were in the list (incrementing their stock)
                for(int i = 0; i< partList.getModel().getSize();i++){
                    SparePart tmp = ((SparePart) partList.getModel().getElementAt(i));
                    // Increment Object's Stock
                    tmp.setStock(tmp.getStock() + 1);
                    // Increment DB's Stock
                    controller.updateStock(tmp.getStock(), tmp.getPartCode());
                }

                j.dispose();
                if (LoginGUI.access == 'F') {
                    ForepersonDashboardGUI.main();
                } else {
                    JobSelectionGUI.main();
                }

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

        regNoField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent f) {
                char d = f.getKeyChar();
                //all special characters, and letters, not allowed on a reg plate
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

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // TODO: Check if records are empty
                Job_Controller controller = new Job_Controller();
                String jobType = (String) jobTypeBox.getSelectedItem();
                float duration = Float.parseFloat(durationField.getText());
                String dates = String.valueOf(jDateChooser.getDate());
                String motNo = motField.getText();
                int mileage = Integer.parseInt(mileageField.getText());
                String additionalInfo = detailsField.getText();
                // Create Job in DB
                controller.sendData(jobType, duration, dates, motNo, mileage, additionalInfo, "Incomplete", regNoField.getText());
                // Get ID of newly created Job
                int jobID = controller.getJobID(jobType, duration, dates, motNo, mileage, additionalInfo, "Incomplete", regNoField.getText());
                // Add Parts from List to Job (Job_SpareParts)
                for(int i = 0; i< partList.getModel().getSize();i++){
                    controller.addToJob(jobID, ((SparePart) partList.getModel().getElementAt(i)).getPartCode());
                }
                j.dispose();
                if (LoginGUI.access == 'F') {
                    ForepersonDashboardGUI.main();
                } else {
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
                        // Decrement stock
                        controller.updateStock(((SparePart) partSelectBox.getSelectedItem()).getStock(), ((SparePart)  partSelectBox.getSelectedItem()).getPartCode());
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
                    // Increment Object's stock
                    ((SparePart) partList.getSelectedValue()).setStock(((SparePart) partList.getSelectedValue()).getStock() + 1);

                    // Update database to Object's incremented stock
                    Job_Controller controller = new Job_Controller();
                    controller.updateStock(((SparePart) partList.getSelectedValue()).getStock(), ((SparePart)  partList.getSelectedValue()).getPartCode());

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

    // TODO: What happens to the added spare parts if the application is closed?
    public static void main(){
        j.setContentPane(new CreateJobGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Job Creation Menu");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(1280,720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    // Need to use this method because intelliJ's createUIcomponents causes issues as always
    private void configureList() {
        Job_Controller controller = new Job_Controller();
        SparePart[] parts = controller.getAllParts();

        DefaultComboBoxModel<SparePart> model = new DefaultComboBoxModel<SparePart>(parts);
        partSelectBox.setModel(model);
    }
    private void createUIComponents(){
        jDateChooser = new JDateChooser();


        // Configure List of added parts
        this.partModel = new DefaultListModel<SparePart>();
        partList = new JList<>(partModel);
        partList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Dropdown of possible parts to add to job
        // TODO: filter this to become vehicle-specific (manufacturer/model)

        partSelectBox = new JComboBox<>();

    }
}

