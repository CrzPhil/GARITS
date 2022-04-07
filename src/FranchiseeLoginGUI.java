import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FranchiseeLoginGUI extends JFrame{
    private JPanel Main;
    private JButton enterButton;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton returnButton;
    private static FranchiseeLoginGUI j = new FranchiseeLoginGUI();

    public FranchiseeLoginGUI() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String password = String.valueOf(txtPassword.getPassword());
                if (password.equals("franchiseepass")) {
                    j.dispose();
                    CustomerAccountMenuGUI.main();
                } else {
                    // Error pop up for wrong password
                    JOptionPane.showMessageDialog(null, "Wrong Password!");
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                MainMenuGUI.main();
            }
        });
    }
    public static void main(){
        j.setContentPane(new FranchiseeLoginGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Please Enter Credentials.");
        j.setPreferredSize(new Dimension(1280, 720));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);

    }

}
