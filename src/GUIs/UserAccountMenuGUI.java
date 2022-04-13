package GUIs;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Accounts.SQL_UserHelper;
import Accounts.User;

public class UserAccountMenuGUI extends JFrame{
    private JPanel Main;
    private JButton logoutButton;
    private JButton modifyAccountButton;
    private JTree UserAccounts;
    private JButton createButton;
    private JLabel titleLabel;
    private User selectedAccount;
    private static final UserAccountMenuGUI j = new UserAccountMenuGUI();

    public UserAccountMenuGUI() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                AdminDashboardGUI.main();
            }
        });
        modifyAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedAccount != null) {
                    j.dispose();
                    UserAccountModifierGUI.main(selectedAccount);
                } else {
                    JOptionPane.showMessageDialog(null, "Select an account first!");
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountCreationGUI.main();
            }
        });
    }

    private void drawTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Users");
        createNodes(top);
        UserAccounts = new JTree(top);
        UserAccounts.setRowHeight(40);
        UserAccounts.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        UserAccounts.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Store selected account
        UserAccounts.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) UserAccounts.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                // Only leaf nodes are accounts
                // TODO: If a superclass has no leaf nodes, we get an error w/o crash
                if (node.isLeaf() && node.getUserObject() != null)
                    selectedAccount = (User) node.getUserObject();
            }
        });
    }

    private void createNodes(DefaultMutableTreeNode top) {
        String[] roles = {"Administrator", "Franchisee", "Mechanic", "Foreperson", "Receptionist"};
        SQL_UserHelper helper = new SQL_UserHelper();
        DefaultMutableTreeNode category;

        // Create root Node for each role
        for (String role : roles) {
            // Category name
            category = new DefaultMutableTreeNode(role + "Accounts");
            top.add(category);

            // Get all the users of a certain role from the SQL helper
            User[] users = helper.getByRole(role);
            for (User user : users) {
                category.add(new DefaultMutableTreeNode(user));
            }
        }

        helper.closeConnection();
    }

    public static void main(){
        j.setContentPane(new UserAccountMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("User Account Library");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        drawTree();
    }
}
