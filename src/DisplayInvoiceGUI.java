import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayInvoiceGUI extends JFrame{
    private JPanel main;
    private JButton downloadButton;
    private JButton closeButton;
    private JLabel invoiceImage;
    private static DisplayInvoiceGUI j = new DisplayInvoiceGUI();

    public DisplayInvoiceGUI() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(){
        j.setContentPane(new DisplayInvoiceGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Invoice");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setSize(new Dimension(new ImageIcon("src/Reports/preview_page1.png").getIconWidth(), new ImageIcon("src/Reports/preview_page1.png").getIconHeight()));
        j.setVisible(true);
    }

    private void createUIComponents() {
            invoiceImage = new JLabel(new ImageIcon("src/Reports/preview_page1.png"));
        }
    }

