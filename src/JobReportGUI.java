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
                    try {
                        // Current working directory
                        String cdir = System.getProperty("user.dir");

                        String type;
                        if (overallCheckBox.isSelected()) {
                            type = "Overall";
                        } else {
                            type = String.valueOf(jobTypeBox.getSelectedItem());
                        }

                        // Python process, passing type (Overall OR MOT/Service/Repair) to program
                        ProcessBuilder pb = new ProcessBuilder("python3", cdir + "/src/Reports/JobReport/jobreportgenerator.py", type);
                        Process p = pb.start();
                        p.waitFor();

                        // DEBUG ONLY
                        //BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        //System.out.println(in.readLine());

                        String filename = java.time.LocalDate.now() + "-" + type + "-jobReport.pdf";
                        DisplayReportGUI.main(cdir + "/src/Reports/JobReport", filename);
                    } catch (Exception ignore) {
                        JOptionPane.showMessageDialog(null, "Something went wrong. Contact your administrator.");
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

    private void createUIComponents() {
        overallCheckBox = new JCheckBox();
    }
}
