import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewJobsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTree Jobs;
    private JButton detailsButton;
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
}
