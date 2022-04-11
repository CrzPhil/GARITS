import Job.Job;
import Job.Job_Controller;
import Job.CompletedJob_Controller;
import Job.CompletedJob;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompletedJobsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JButton detailsButton;
    private JList jobList;
    private JLabel titleLabel;
    private static ViewCompletedJobsGUI j = new ViewCompletedJobsGUI();
    private CompletedJob selectedJob = null;

    public ViewCompletedJobsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobSelectionGUI.main();
            }
        });
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedJob != null) {
                    j.dispose();
                    // Pass the job to the next GUI
                    CompletedJobDetailsGUI.main(selectedJob);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a job first!");
                }
            }
        });
    }
    public static void main(){
        j.setContentPane(new ViewCompletedJobsGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Completed Jobs Library");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        CompletedJob_Controller controller = new CompletedJob_Controller();
        CompletedJob[] jobs = controller.getJobs();

        DefaultListModel<CompletedJob> jobModel = new DefaultListModel<CompletedJob>();

        // Add jobs to model
        for (CompletedJob job : jobs) {
            jobModel.addElement(job);
        }

        // Specify jobList
        jobList = new JList<>(jobModel);
        jobList.setFixedCellHeight(80);
        jobList.setFont(new Font("monospaced", Font.PLAIN, 18));
        jobList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Listener for when an item is selected
        jobList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedJob = (CompletedJob) jobList.getSelectedValue();
                }
            }
        });
    }
}
