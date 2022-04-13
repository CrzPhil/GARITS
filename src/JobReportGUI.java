import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobReportGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JCheckBox overallCheckBox;
    private JButton generateReportButton;
    private JComboBox jobTypeBox;
    private static JobReportGUI j = new JobReportGUI();

    public JobReportGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ReportsMenuGUI.main();
            }
        });
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((overallCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Overall report chosen.");
                    //enter code for overall report generation
                    DisplayReportGUI.main();
                }else{
                    DisplayReportGUI.main();
                }
            }
        });
        jobTypeBox.addItem("MOT");
        jobTypeBox.addItem("Service");
        jobTypeBox.addItem("Repair");
    }
    public static void main(){
        j.setContentPane(new JobReportGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Generate Price/Time Report");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
