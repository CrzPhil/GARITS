import Job.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobDetailsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField jobIDField;
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
    private static JobDetailsGUI j = new JobDetailsGUI();
    private Job job;

    public JobDetailsGUI(Job job) {
        this.job = job;
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
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
            jobIDField = new JTextField(String.valueOf(job.getJobID()));
            statusField = new JTextField(job.getStatus());
            typeField = new JTextField(job.getJobType());
            priceField = new JTextField(String.valueOf(job.getPrice()));
            motField = new JTextField(job.getMotNO());
            regNoField = new JTextField();
            dateField = new JTextField(job.getDates());
            durationField = new JTextField(String.valueOf(job.getDuration()));
            requiredPartsField = new JTextField(job.getRequiredParts());
            mileageField = new JTextField(String.valueOf(job.getMileage()));
        } else {
            jobIDField = new JTextField();
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
