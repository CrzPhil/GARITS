import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Job.SQL_PartsHelper;
import Job.SparePart;

public class SearchResultsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTable resultTable;
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

    private Object[][] prepareData(SparePart[] data) {
        // 7 is attribute count of a spare part
        Object[][] out = new Object[data.length][7] ;
        int i = 0;
        for (SparePart part : data) {
            out[i] = new Object[]{part};

            ++i;
        }

        return out;
    }

    private void generateTable() {
        String[] columnNames = {
                "Part ID",
                "Part Name",
                "Manufacturer",
                "Vehicle Type",
                "Year",
                "Stock",
                "Price"
        };
        SparePart[] data = getParts();
        resultTable = new JTable(prepareData(data), columnNames);
    }

    private SparePart[] getParts() {
        SQL_PartsHelper helper = new SQL_PartsHelper();
        return helper.getByID("'%'");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        generateTable();
    }
}
