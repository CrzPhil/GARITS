package GUIs;

import Job.SQL_PartsHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class ItemSearchGUI extends JFrame {
    private JTextField txtPartName;
    private JButton searchButton;
    private JTextField txtPartType;
    private JButton searchButton1;
    private JPanel Main;
    private JComboBox typeBox;
    private JButton returnButton;
    private JButton newSparePartButton;
    private JButton searchAllButton;
    private static ItemSearchGUI j = new ItemSearchGUI();

    public ItemSearchGUI() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String partName = txtPartName.getText();

                if (!partName.equals("")) {
                    SearchResultsGUI.main(partName);
                    j.dispose();

                } else if (!typeBox.getItemAt(typeBox.getSelectedIndex()).equals("")) {
                    SearchResultsGUI.main((String) typeBox.getItemAt(typeBox.getSelectedIndex()));
                    j.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No input in search fields.");
                }
            }
        });

        searchAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                j.dispose();
                SearchResultsGUI.main();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                if (LoginGUI.access == 'C') {
                    FranchiseeDashboardGUI.main();
                } else if (LoginGUI.access == 'R') {
                    ReceptionistDashboardGUI.main();
                } else {
                    MainMenuGUI.main();
                }
            }
        });

        newSparePartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CreateSparePartGUI.main();
            }
        });
    }

    public static void main() {
        j.setContentPane(new ItemSearchGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Spare Parts Catalogue");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }


    private void createUIComponents() {
        SQL_PartsHelper helper = new SQL_PartsHelper();

        String[] types = helper.getTypes();

        typeBox = new JComboBox(types);
        newSparePartButton = new JButton();
        try {
            Image nImg = ImageIO.read(new FileInputStream("data/newpart.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            newSparePartButton.setIcon(new ImageIcon(nImg));
        } catch (Exception ignored) {
        }
        newSparePartButton.setBorder(null);
        newSparePartButton.setBorderPainted(false);
        newSparePartButton.setMargin(new Insets(0, 0, 0, 0));
        newSparePartButton.setContentAreaFilled(false);
        newSparePartButton.setFocusPainted(false);


    }
}
