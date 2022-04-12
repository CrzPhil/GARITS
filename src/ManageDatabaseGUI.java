import Database.Database_Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageDatabaseGUI extends JFrame {
    private JPanel Main;
    private JLabel titleLabel;
    private JButton backupButton;
    private JButton restoreButton;
    private JButton returnButton;
    private static ManageDatabaseGUI j = new ManageDatabaseGUI();
    private static Database_Controller controller = new Database_Controller();

    public ManageDatabaseGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                AdminDashboardGUI.main();
            }
        });
        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.backupDatabase()) {
                    JOptionPane.showMessageDialog(null, "Successfully backed up database.");
                } else {
                    JOptionPane.showMessageDialog(null, "Couldn't back up database.\n Is MySQL installed & added to PATH?");
                }
            }
        });
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set file chooser to default backup location
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir")+"/src/Database/Backups");

                // Configure file chooser
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.setDialogTitle("Choose a file to rollback the database to.");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                // Set filter for .sql extensions
                fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
                fileChooser.setFileFilter(new FileNameExtensionFilter("Files ending in .sql", "sql"));

                // Change approve button text
                fileChooser.setApproveButtonText("Select");

                // Get the backup file and attempt rollback
                int choice = fileChooser.showSaveDialog(null);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    if (controller.restoreDatabase(fileChooser.getSelectedFile().getPath())) {
                        JOptionPane.showMessageDialog(null, "Successfully restored database.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Rollback failed.\n Is MySQL installed & added to PATH?");
                    }
                }
            }
        });
    }

    public static void main(){
        j.setContentPane(new ManageDatabaseGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Manage Database");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(1280,720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
