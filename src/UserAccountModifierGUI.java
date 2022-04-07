import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAccountModifierGUI extends JFrame{
    private JPanel Main;
    private JButton modifyUserameButton;
    private JButton modifyEmailAddressButton;
    private JButton modifyPasswordButton;
    private JButton modifyTypeButton;
    private JButton modifyPermissionsButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton returnButton;
    private JTextField textField6;
    private JButton modifyUserIDButton;
    private static UserAccountModifierGUI j = new UserAccountModifierGUI();

    public UserAccountModifierGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                UserAccountMenuGUI.main();
            }
        });
    }

    public static void main(){
        j.setContentPane(new UserAccountModifierGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Making Account Changes");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
