import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DisplayReportGUI extends JFrame {

    private JPanel main;
    private JButton closeButton;
    private JButton downloadButton;
    private JLabel ReportImage;
    private static DisplayReportGUI j = new DisplayReportGUI();

    private String filepath;
    private String filename;

    public DisplayReportGUI() {}

    public DisplayReportGUI(String filepath, String filename) {
        this.filename = filename;
        this.filepath = filepath;
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Decide what to do here
                // Clean up files, so that only pdf remains
/*                String cdir = System.getProperty("user.dir");
                File myObj = new File(cdir + "/src/Reports/preview_page1.png");

                // If deletion does not happen (i.e if the user closes the program instead of returning,
                // it is not critical, as it will be overwritten by the next report.
                myObj.delete();*/

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
                            //String filename = java.time.LocalDate.now() + "-report.pdf";
                            //String cdir = System.getProperty("user.dir");

                            // Copy bits from pdf
                            in = new FileInputStream( filepath + '/' + filename);
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



    public static void main(String filepath, String filename){
        j.setContentPane(new DisplayReportGUI(filepath, filename).main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Report");
            Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
            j.setIconImage(icon);
        //j.setPreferredSize(new Dimension(700, 990));
        j.pack();
        j.setLocationRelativeTo(null);
        // Make the report fit into the screen
        j.setSize(new Dimension(new ImageIcon(filepath + "/preview_page1.png").getIconWidth(), new ImageIcon(filepath + "/preview_page1.png").getIconHeight()));
        j.setVisible(true);
}
    private void createUIComponents() {
        ReportImage = new JLabel(new ImageIcon(filepath + "/preview_page1.png"));
    }


}
