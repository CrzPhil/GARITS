import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Job.SQL_PartsHelper;
import Job.SparePart;

public class SearchResultsGUI extends JFrame{
    JPanel Main;
    private JButton returnButton;
    private JTable resultTable;
    private JScrollPane scrollPane;
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
        j.Main.setOpaque(true);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private String[][] prepareData(SparePart[] data) {
        // 7 is attribute count of a spare part
        String[][] out = new String[data.length][7] ;
        int i = 0;
        for (SparePart part : data) {
            out[i] = part.toData();

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

        scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
    }

    private SparePart[] getParts() {
        SQL_PartsHelper helper = new SQL_PartsHelper();
        // TODO: Get text from previous Panel and search accordingly
        return helper.getByID("'%'");
    }

    private void createUIComponents() {
        generateTable();
    }
}
