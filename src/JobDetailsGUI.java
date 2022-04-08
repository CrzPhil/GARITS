import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobDetailsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextArea textArea1;
    private JButton saveChangesButton;
    private static JobDetailsGUI j = new JobDetailsGUI();

    public JobDetailsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new JobDetailsGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Chosen Job Details");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }


}
