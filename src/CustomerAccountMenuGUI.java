import Accounts.SQL_UserHelper;
import Accounts.User;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountMenuGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JButton modifyAccountButton;
    private JTree CustomerAccounts;
    private JButton createAccountButton;
    private static final CustomerAccountMenuGUI j = new CustomerAccountMenuGUI();

    public CustomerAccountMenuGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                MainMenuGUI.main();
            }
        });
        modifyAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountModifierGUI.main();
            }
        });
    }

    private void drawTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Customers");
        createNodes(top);
        CustomerAccounts = new JTree(top);
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
        j.setContentPane(new CustomerAccountMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Customer Account Library");
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
