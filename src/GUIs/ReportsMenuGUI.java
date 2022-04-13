package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsMenuGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JButton numberOfVehiclesBookedButton;
    private JButton averageTimeAndPriceButton;
    private JButton stockLevelButton;
    private static ReportsMenuGUI j = new ReportsMenuGUI();

    public ReportsMenuGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   j.dispose();
                if (LoginGUI.access == 'C') {
                    FranchiseeDashboardGUI.main();
                } else {
                    MainMenuGUI.main();
                }
            }
        });
        numberOfVehiclesBookedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                VehicleReportGUI.main();
            }
        });
        averageTimeAndPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                JobReportGUI.main();
            }
        });
        stockLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                StockReportGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new ReportsMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Report Menu");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
