import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
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
    private static final UserAccountMenuGUI j = new UserAccountMenuGUI();

    public UserAccountMenuGUI() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                LoginGUI.main();
            }
        });
        modifyAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountModifierGUI.main();
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
    }

    private void createNodes(DefaultMutableTreeNode top) {
        String[] roles = {"Administrator", "Franchisee", "Mechanic", "Foreperson", "Receptionist"};
        SQL_UserHelper helper = new SQL_UserHelper();
        DefaultMutableTreeNode category;

        for (String role : roles) {
            category = new DefaultMutableTreeNode(role + " Accounts");
            top.add(category);
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
