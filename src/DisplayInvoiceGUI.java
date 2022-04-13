import Job.Job;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DisplayInvoiceGUI extends JFrame{
    private JPanel main;
    private JButton downloadButton;
    private JButton closeButton;
    private JLabel invoiceImage;
    private static DisplayInvoiceGUI j = new DisplayInvoiceGUI();
    private Job job;

    public DisplayInvoiceGUI() {}

    public DisplayInvoiceGUI(Job job) {
        this.job = job;
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Choose a directory to save the report: ");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int choice = fileChooser.showSaveDialog(null);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    if (fileChooser.getSelectedFile().isDirectory()) {
                        InputStream in = null;

                        // TODO: taken from https://stackoverflow.com/questions/1146153/copying-files-from-one-directory-to-another-in-java
                        try {
                            // Get python-generated report pdf file
                            String filename = String.valueOf(job.getJobID()) + "-invoice.pdf";
                            String cdir = System.getProperty("user.dir");

                            // Copy bits from pdf
                            in = new FileInputStream(cdir + "/src/Invoices/" + filename);
                            // Outstream to write file to new directory
                            OutputStream out = new FileOutputStream(fileChooser.getSelectedFile() + "/" + filename);

                            // Copy Instream to Outstream
                            byte[] buf = new byte[1024];
                            int len;

                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }

                            in.close();
                            out.close();

                            JOptionPane.showMessageDialog(null, "File successfully saved to " + fileChooser.getSelectedFile(), "Success", JOptionPane.PLAIN_MESSAGE);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }
        });
    }

    public static void main(Job job){
        j.setContentPane(new DisplayInvoiceGUI(job).main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Invoice");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setSize(new Dimension(new ImageIcon("src/Invoices/preview_page1.png").getIconWidth(), new ImageIcon("src/Invoices/preview_page1.png").getIconHeight()));
        j.setVisible(true);
    }

    private void createUIComponents() {
            invoiceImage = new JLabel(new ImageIcon("src/Invoices/preview_page1.png"));
        }
    }

