import Job.SQL_JobHelper;

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
    private static CreateJobGUI j = new CreateJobGUI();

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
                    String dates = dateField.getText();
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
}
