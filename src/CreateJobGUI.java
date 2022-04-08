import Job.SQL_JobHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateJobGUI extends JFrame{
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
    private JButton finishButton;
    private JTextArea textArea1;
    private static CreateJobGUI j = new CreateJobGUI();

    public CreateJobGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobsMenuGUI.main();
            }
        });

        textField4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                    JOptionPane.showMessageDialog(null, "Numbers Only!");
                }
            }
        });

        textField7.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                    JOptionPane.showMessageDialog(null, "Numbers Only!");
                }
            }
        });

        textField1.addKeyListener(new KeyAdapter() {
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

                SQL_JobHelper sqlJob = new SQL_JobHelper();
                String jobType = textField3.getText();
                float duration = Float.parseFloat(textField4.getText());
                String dates = textField2.getText();
                String parts = textField5.getText();
                String motNo = textField6.getText();
                int mileage = Integer.parseInt(textField7.getText());
                float price = Float.parseFloat(textField1.getText());
                String requiredParts = textField5.getText();
                String additionalInfo = textArea1.getText();

                sqlJob.sendData(jobType, duration, dates, parts, motNo, mileage, price, requiredParts, additionalInfo);
                j.dispose();
                JobsMenuGUI.main();
            }
        });
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
