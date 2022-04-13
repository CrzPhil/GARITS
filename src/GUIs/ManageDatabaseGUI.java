package GUIs;

import Database.Database_Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

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
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        backupButton = new JButton();
        restoreButton = new JButton();
        try {
            Image bImg = ImageIO.read(new FileInputStream("data/backup.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            Image nImg = ImageIO.read(new FileInputStream("data/restore.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            backupButton.setIcon(new ImageIcon(bImg));
            restoreButton.setIcon(new ImageIcon(nImg));
        } catch (Exception ignored) {
        }
        backupButton.setBorder(null);
        backupButton.setBorderPainted(false);
        backupButton.setMargin(new Insets(0, 0, 0, 0));
        backupButton.setContentAreaFilled(false);
        backupButton.setFocusPainted(false);

        restoreButton.setBorder(null);
        restoreButton.setBorderPainted(false);
        restoreButton.setMargin(new Insets(0, 0, 0, 0));
        restoreButton.setContentAreaFilled(false);
        restoreButton.setFocusPainted(false);
    }
}
