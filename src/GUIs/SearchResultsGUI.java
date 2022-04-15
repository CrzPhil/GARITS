package GUIs;

import Job.SQL_PartsHelper;
import Job.SparePart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResultsGUI extends JFrame{
    JPanel Main;
    private JButton returnButton;
    private JTable resultTable;
    private JScrollPane scrollPane;
    private JButton editItemButton;
    private JButton incrementStockButton;
    private JButton decrementStockButton;
    private static SearchResultsGUI j = new SearchResultsGUI();
    private String partName;
    private String typeName;

    public SearchResultsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ItemSearchGUI.main();



            }
        });

        editItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                // If nothing is selected, getSelectedRow() returns -1
                if (row != -1) {
                    // Call edit GUI with selected row's details
                    EditSparePartGUI.main(new SparePart(
                            resultTable.getModel().getValueAt(row, 0).toString(),
                            resultTable.getModel().getValueAt(row, 1).toString(),
                            resultTable.getModel().getValueAt(row, 2).toString(),
                            resultTable.getModel().getValueAt(row, 3).toString(),
                            Integer.parseInt(resultTable.getModel().getValueAt(row, 4).toString()),
                            Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()),
                            Float.parseFloat(resultTable.getModel().getValueAt(row, 6).toString()),
                            Integer.parseInt(resultTable.getModel().getValueAt(row, 7).toString())));
                    j.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Select a part first!");
                }
            }
        });

        incrementStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                // If nothing is selected, getSelectedRow() returns -1
                if (row != -1) {
                    // Update Table
                    resultTable.getModel().setValueAt(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()) + 1,
                            row,
                            5);
                    SQL_PartsHelper helper = new SQL_PartsHelper();
                    // Update DB using current stock and partCode
                    helper.updateStock(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()),
                            resultTable.getModel().getValueAt(row, 0).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Select a part first!");
                }
            }
        });

        decrementStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                // If nothing is selected, getSelectedRow() returns -1
                if (row != -1) {
                    SQL_PartsHelper helper = new SQL_PartsHelper();
                    if (Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Stock depleted!");
                    } else {
                        // Update Table
                        resultTable.getModel().setValueAt(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()) - 1,
                                row,
                                5);

                        // Update DB using current stock and partCode
                        helper.updateStock(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()),
                                resultTable.getModel().getValueAt(row, 0).toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a part first!");
                }
            }
        });
    }

    public SearchResultsGUI(String partName) {
        this.partName = partName;

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ItemSearchGUI.main();



            }
        });

        editItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                // If nothing is selected, getSelectedRow() returns -1
                if (row != -1) {
                    // Call edit GUI with selected row's details
                    EditSparePartGUI.main(new SparePart(
                            resultTable.getModel().getValueAt(row, 0).toString(),
                            resultTable.getModel().getValueAt(row, 1).toString(),
                            resultTable.getModel().getValueAt(row, 2).toString(),
                            resultTable.getModel().getValueAt(row, 3).toString(),
                            Integer.parseInt(resultTable.getModel().getValueAt(row, 4).toString()),
                            Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()),
                            Float.parseFloat(resultTable.getModel().getValueAt(row, 6).toString()),
                            Integer.parseInt(resultTable.getModel().getValueAt(row, 7).toString())));
                    j.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Select a part first!");
                }
            }
        });

        incrementStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                // If nothing is selected, getSelectedRow() returns -1
                if (row != -1) {
                    // Update Table
                    resultTable.getModel().setValueAt(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()) + 1,
                            row,
                            5);
                    SQL_PartsHelper helper = new SQL_PartsHelper();
                    // Update DB using current stock and partCode
                    helper.updateStock(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()),
                            resultTable.getModel().getValueAt(row, 0).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Select a part first!");
                }
            }
        });

        decrementStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                // If nothing is selected, getSelectedRow() returns -1
                if (row != -1) {
                    SQL_PartsHelper helper = new SQL_PartsHelper();
                    if (Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Stock depleted!");
                    } else {
                        // Update Table
                        resultTable.getModel().setValueAt(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()) - 1,
                                row,
                                5);

                        // Update DB using current stock and partCode
                        helper.updateStock(Integer.parseInt(resultTable.getModel().getValueAt(row, 5).toString()),
                                resultTable.getModel().getValueAt(row, 0).toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a part first!");
                }
            }
        });
    }

    public static void main(){
        j.setContentPane(new SearchResultsGUI().Main);
        createFrame();
    }

    public static void main(String partName){
        j.setContentPane(new SearchResultsGUI(partName).Main);
        createFrame();
    }

    private static void createFrame() {
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Search results");
        Image icon = FindImages.getImageLogo();
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800,480));
        j.Main.setOpaque(true);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    /**
     * Prepare Data for JTable
     * @param data SparePart Array
     * @return String 2d-array
     */
    private String[][] prepareData(SparePart[] data) {
        // 8 is attribute count of a spare part
        String[][] out = new String[data.length][8] ;
        int i = 0;
        for (SparePart part : data) {
            out[i] = part.toData();

            ++i;
        }

        return out;
    }

    /**
     * Generate JTable
     */
    private void generateTable() {
        String[] columnNames = {
                "Part ID",
                "Part Name",
                "Manufacturer",
                "Vehicle Type",
                "Year",
                "Stock",
                "Price",
                "Threshold"
        };
        SparePart[] data = getParts();

        //instance table model -> Disable editable cells.
        DefaultTableModel tableModel = new DefaultTableModel(prepareData(data), columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        resultTable = new JTable();
        resultTable.setModel(tableModel);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
    }

    /**
     * Get All parts by previous' GUI's search parameter
     * @return SparePart array
     */
    private SparePart[] getParts() {
        SQL_PartsHelper helper = new SQL_PartsHelper();
        if(partName == null){
            return helper.getAllParts();
        }
        return helper.getPartByIdName(this.partName);
    }

    private void createUIComponents() {
        generateTable();
    }
}
