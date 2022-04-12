import Job.Job;
import Job.Job_Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewJobsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JButton detailsButton;
    private JList jobList;
    private JLabel titleLabel;
    private static ViewJobsGUI j = new ViewJobsGUI();
    private Job selectedJob = null;

    public ViewJobsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                if (LoginGUI.access == 'M') {
                    MechanicDashboardGUI.main();
                } else if (LoginGUI.access == 'F'){
                    ForepersonDashboardGUI.main();
                } else {
                    JobSelectionGUI.main();
                }
            }
        });
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedJob != null) {
                    j.dispose();
                    // Pass the job to the next GUI
                    JobDetailsGUI.main(selectedJob);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a job first!");
                }
            }
        });
    }
    public static void main(){
        j.setContentPane(new ViewJobsGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Jobs Library");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(1000, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        Job_Controller controller = new Job_Controller();
        Job[] jobs = controller.getJobs();

        DefaultListModel<Job> jobModel = new DefaultListModel<Job>();

        // Add jobs to model
        for (Job job : jobs) {
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
                    selectedJob = (Job) jobList.getSelectedValue();
                }
            }
        });
    }
}
