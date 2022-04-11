import Job.Job;
import Job.CompletedJob;
import Job.SQL_JobHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CompletedJobDetailsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField typeField;
    private JTextField durationField;
    private JTextField priceField;
    private JTextField dateField;
    private JTextField requiredPartsField;
    private JTextField mileageField;
    private JTextField motField;
    private JTextField regNoField;
    private JTextArea additionalField;
    private JButton saveChangesButton;
    private JLabel jobIDLabel;
    private JLabel idLabel;
    private JComboBox statusBox;
    private JComboBox jobTypeBox;
    private static CompletedJobDetailsGUI j = new CompletedJobDetailsGUI();
    private CompletedJob job;

    public CompletedJobDetailsGUI(CompletedJob job) {
        this.job = job;
        jobIDLabel.setText(String.valueOf(job.getJobID()));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewCompletedJobsGUI.main();
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to make these changes?", "Confirm changes", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (inputValid(typeField.getText(),
                            durationField.getText(),
                            mileageField.getText(),
                            priceField.getText())) {
                        SQL_JobHelper helper = new SQL_JobHelper();
                        if (helper.updateCompletedJob(
                                job.getJobID(),
                                (String) jobTypeBox.getSelectedItem(),
                                Float.parseFloat(durationField.getText()),
                                dateField.getText(),
                                requiredPartsField.getText(),
                                motField.getText(),
                                Integer.parseInt(mileageField.getText()),
                                Float.parseFloat(priceField.getText()),
                                additionalField.getText(),
                                (String) statusBox.getSelectedItem())) {
                            // Successful Job update
                            JOptionPane.showMessageDialog(null, "Job updated successfully.");
                            j.dispose();
                            ViewCompletedJobsGUI.main();
                        } else {
                            JOptionPane.showMessageDialog(null, "Something went wrong.\n Double-check your values.");
                        }
                    }
                    // If input invalid
                    else {
                        JOptionPane.showMessageDialog(null, "Please verify your input and try again.");
                    }
                }
            }
        });
    }

    private boolean inputValid(String type, String duration, String mileage, String price) {
        // Check if Type is valid
        if (!job.getJobTypes().contains(type)) {
            return false;
        }
        // Check for number formats
        try {
            Float.parseFloat(duration);
            Float.parseFloat(price);
            Integer.parseInt(mileage);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public CompletedJobDetailsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewCompletedJobsGUI.main();
            }
        });
    }

    public static void main(CompletedJob job){
        j.setContentPane(new CompletedJobDetailsGUI(job).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Chosen Completed Job Details");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    // TODO: Registration Number ?
    private void createUIComponents() {
        if (job != null) {
            // For some reason label does not get updated here, and we have to repeat this in the constructor
            // jobID is not modifiable, as it is a PK in the database and has to be unique
            jobIDLabel = new JLabel(String.valueOf(job.getJobID()));

            // Create text fields
            typeField = new JTextField(job.getJobType());
            priceField = new JTextField(String.valueOf(job.getPrice()));
            motField = new JTextField(job.getMotNO());
            regNoField = new JTextField();
            dateField = new JTextField(job.getDates());
            durationField = new JTextField(String.valueOf(job.getDuration()));
            requiredPartsField = new JTextField(job.getParts());
            mileageField = new JTextField(String.valueOf(job.getMileage()));

            // Create combobox for status

            // Configure which option to show
            String otherOption;
            if (Objects.equals(job.getStatus(), job.getStates()[0])) {
                otherOption = job.getStates()[1];
            } else {
                otherOption = job.getStates()[0];
            }

            statusBox = new JComboBox<String>(new String[]{job.getStatus(), otherOption});

        } else {
            jobIDLabel = new JLabel();
            typeField = new JTextField();
            priceField = new JTextField();
            motField = new JTextField();
            regNoField = new JTextField();
            dateField = new JTextField();
            durationField = new JTextField();
            requiredPartsField = new JTextField();
            mileageField = new JTextField();
            statusBox = new JComboBox<String>();
        }
    }
}
