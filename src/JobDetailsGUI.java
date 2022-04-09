import Job.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Job.SQL_JobHelper;

public class JobDetailsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField statusField;
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
    private static JobDetailsGUI j = new JobDetailsGUI();
    private Job job;

    public JobDetailsGUI(Job job) {
        this.job = job;
        jobIDLabel.setText(String.valueOf(job.getJobID()));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
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
                        if (helper.updateJob(
                                job.getJobID(),
                                typeField.getText(),
                                Float.parseFloat(durationField.getText()),
                                dateField.getText(),
                                requiredPartsField.getText(),
                                motField.getText(),
                                Integer.parseInt(mileageField.getText()),
                                Float.parseFloat(priceField.getText()),
                                additionalField.getText())) {
                            // Successful Job update
                            JOptionPane.showMessageDialog(null, "Job updated successfully.");
                            j.dispose();
                            ViewJobsGUI.main();
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

    public JobDetailsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
    }

    public static void main(Job job){
        j.setContentPane(new JobDetailsGUI(job).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Chosen Job Details");
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
            statusField = new JTextField(job.getStatus());
            typeField = new JTextField(job.getJobType());
            priceField = new JTextField(String.valueOf(job.getPrice()));
            motField = new JTextField(job.getMotNO());
            regNoField = new JTextField();
            dateField = new JTextField(job.getDates());
            durationField = new JTextField(String.valueOf(job.getDuration()));
            requiredPartsField = new JTextField(job.getParts());
            mileageField = new JTextField(String.valueOf(job.getMileage()));
        } else {
            jobIDLabel = new JLabel();
            statusField = new JTextField();
            typeField = new JTextField();
            priceField = new JTextField();
            motField = new JTextField();
            regNoField = new JTextField();
            dateField = new JTextField();
            durationField = new JTextField();
            requiredPartsField = new JTextField();
            mileageField = new JTextField();
        }
    }
}
