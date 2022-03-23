import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountModifierGUI extends JFrame{
    private JPanel Main;
    private JButton modifyUserameButton;
    private JButton modifyEmailAddressButton;
    private JButton modifyPasswordButton;
    private JButton modifyTypeButton;
    private JButton modifyPerrmissionsButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton returnButton;
    private static AccountModifierGUI j = new AccountModifierGUI();

    public AccountModifierGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                AccountMenuGUI.main();
            }
        });
    }

    public static void main(){
        j.setContentPane(new AccountModifierGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Making Account Changes");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
