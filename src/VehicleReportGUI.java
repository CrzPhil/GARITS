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
               /* j.dispose();
                ReportsMenuGUI.main(); */
                if(!(monthlyCheckBox.isSelected()) && !(perJobTypeCheckBox.isSelected()) && !(overallCheckBox.isSelected()) && !(perCustomerTypeCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select a filter and timeframe option.");
                }else if ((overallCheckBox.isSelected()) && (monthlyCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select only one timeframe option.");
                }else if ((perJobTypeCheckBox.isSelected()) && (perCustomerTypeCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select only one filter option.");
                }else if (!(overallCheckBox.isSelected()) && !(monthlyCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select a timeframe option.");
                }else if (!(perJobTypeCheckBox.isSelected()) && !(perCustomerTypeCheckBox.isSelected())){
                    JOptionPane.showMessageDialog(null, "Select a filter option.");
                }else{
                    //DisplayReportGUI.main();
                }
            }
        });
    }
    public static void main(){
        j.setContentPane(new VehicleReportGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Generate Vehicle Quantity Report");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

}
