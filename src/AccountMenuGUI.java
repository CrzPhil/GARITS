import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountMenuGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JButton modifyAccountButton;
    private JTree UserAccounts;
    private static AccountMenuGUI j = new AccountMenuGUI();

    public AccountMenuGUI() {
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
                AccountModifierGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new AccountMenuGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Account Library");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
