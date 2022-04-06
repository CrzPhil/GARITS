import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobsMenuGUI extends JFrame{
    private JPanel Main;
    private JButton viewJobsButton;
    private JButton createJobsButton;
    private JButton returnButton;
    private JButton createBookingButton;
    private static JobsMenuGUI j = new JobsMenuGUI();

    public JobsMenuGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                MainMenuGUI.main();
            }
        });
        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
        createJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CreateJobGUI.main();
            }
        });
        createBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CreateBookingGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new JobsMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Jobs Menu");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
