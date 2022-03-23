import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResultsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JList searchResults;
    private static SearchResultsGUI j = new SearchResultsGUI();

    public SearchResultsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ItemSearchGUI.main();



            }
        });
    }

    public static void main(){
        j.setContentPane(new SearchResultsGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Search results");
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
