package GUIs;

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
                    try {
                        // Current working directory
                        String cdir = System.getProperty("user.dir");

                        // Python process, passing type (Overall OR MOT/Service/Repair) to program
                        ProcessBuilder pb = new ProcessBuilder("python3", cdir + "/src/Reports/VehicleReport/vehiclereportgenerator.py");
                        Process p = pb.start();
                        p.waitFor();

                        // DEBUG ONLY
                        //BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        //System.out.println(in.readLine());

                        String filename = java.time.LocalDate.now() + "-vehicleReport.pdf";
                        DisplayReportGUI.main(cdir + "/src/Reports/VehicleReport", filename);
                    } catch (Exception ignore) {
                        JOptionPane.showMessageDialog(null, "Something went wrong. Contact your administrator.");
                    }
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
