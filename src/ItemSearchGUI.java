import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSearchGUI extends JFrame {
    private JTextField txtPartName;
    private JButton searchButton;
    private JTextField txtPartType;
    private JButton searchButton1;
    private JPanel Main;
    private JComboBox comboBox1;
    private JButton returnButton;
    private static ItemSearchGUI j = new ItemSearchGUI();

    public ItemSearchGUI() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String partName = txtPartName.getText();
                j.dispose();
                SearchResultsGUI.main();


            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                MainMenuGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new ItemSearchGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Spare Parts Catalogue");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
