package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockReportGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JButton generateReportButton;
    private static StockReportGUI j = new StockReportGUI();

    public StockReportGUI() {
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

                    // Python process
                    ProcessBuilder pb = new ProcessBuilder("python3", cdir + "/src/Reports/PartsReport/reportgenerator.py");
                    Process p = pb.start();

                    // DEBUG ONLY
                    // BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    // System.out.println(in.readLine());
                    String filename = java.time.LocalDate.now() + "-report.pdf";

                    DisplayReportGUI.main(cdir + "/src/Reports/PartsReport", filename);

                } catch (Exception ignore) {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Contact your administrator.");
                }
                // j.dispose();
                // ReportsMenuGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new StockReportGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Generate Stock Level Report");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
