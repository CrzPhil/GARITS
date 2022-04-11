import Job.SQL_PartsHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ItemSearchGUI extends JFrame {
    private JTextField txtPartName;
    private JButton searchButton;
    private JTextField txtPartType;
    private JButton searchButton1;
    private JPanel Main;
    private JComboBox typeBox;
    private JButton returnButton;
    private static ItemSearchGUI j = new ItemSearchGUI();

    public ItemSearchGUI() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String partName = txtPartName.getText();
                j.dispose();

                if (!partName.equals(""))
                    SearchResultsGUI.main(partName);

                else if (!typeBox.getItemAt(typeBox.getSelectedIndex()).equals(""))
                    SearchResultsGUI.main((String) typeBox.getItemAt(typeBox.getSelectedIndex()));

                else
                    SearchResultsGUI.main();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   j.dispose();
                if (LoginGUI.access == 'C') {
                    FranchiseeDashboardGUI.main();
                }else if (LoginGUI.access == 'R'){
                    ReceptionistDashboardGUI.main();
                } else {
                    MainMenuGUI.main();
                }
            }
        });

    }
    public static void main(){
        j.setContentPane(new ItemSearchGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Spare Parts Catalogue");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }


    private void createUIComponents() {
        SQL_PartsHelper helper = new SQL_PartsHelper();

        String[] types = helper.getTypes();

        typeBox = new JComboBox(types);

    }
}
