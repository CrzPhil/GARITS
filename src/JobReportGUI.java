import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobReportGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JCheckBox perJobTypeCheckBox;
    private JCheckBox overallCheckBox;
    private JCheckBox perMechanicCheckBox;
    private JButton generateReportButton;
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
                /*j.dispose();
                ReportsMenuGUI.main(); */
                if(!(perJobTypeCheckBox.isSelected()) && !(overallCheckBox.isSelected()) && !(perMechanicCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select one of the options.");
                }else if ((perJobTypeCheckBox.isSelected()) && (overallCheckBox.isSelected()) && (perMechanicCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select only one of the options.");
                }else if ((perJobTypeCheckBox.isSelected()) && (overallCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select only one of the options.");
                }  else if ((perJobTypeCheckBox.isSelected()) && (perMechanicCheckBox.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Select only one of the options.");
                }  else if ((overallCheckBox.isSelected()) && (perMechanicCheckBox.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Select only one of the options.");
                }else{
                    DisplayReportGUI.main();
                }
            }
        });
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
