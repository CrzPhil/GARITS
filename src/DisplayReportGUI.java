import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayReportGUI extends JFrame {

    private JPanel main;
    private JButton closeButton;
    private JButton downloadButton;
    private JLabel ReportImage;
    private static DisplayReportGUI j = new DisplayReportGUI();

    public DisplayReportGUI() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });
    }

    public static void main(){
    j.setContentPane(new DisplayReportGUI().main);
    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    j.setTitle("Report");
    j.setPreferredSize(new Dimension(700, 990));
    j.pack();
    j.setLocationRelativeTo(null);
    j.setVisible(true);
}

    private void createUIComponents() {ReportImage = new JLabel(new ImageIcon("src/Reports/preview_page1.png"));}
}
