import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleReportGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JCheckBox monthlyCheckBox;
    private JCheckBox perJobTypeCheckBox;
    private JCheckBox overallCheckBox;
    private JCheckBox perCustomerTypeCheckBox;
    private JButton generateReportButton;
    private static VehicleReportGUI j = new VehicleReportGUI();

    public VehicleReportGUI() {
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
                j.dispose();
                ReportsMenuGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new VehicleReportGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Generate Vehicle Quantity Report");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

}
