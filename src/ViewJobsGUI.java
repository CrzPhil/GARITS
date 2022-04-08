import Job.Job;
import Job.Job_Controller;

import javax.swing.*;
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

    public ViewJobsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobsMenuGUI.main();
            }
        });
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobDetailsGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new ViewJobsGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Jobs Library");
        j.setPreferredSize(new Dimension(800, 480));
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

        jobList = new JList<>(jobModel);
        jobList.setFixedCellHeight(80);
        jobList.setFont(new Font("monospaced", Font.PLAIN, 18));
    }
}
