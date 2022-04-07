import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountModifierGUI extends JFrame{
    private JPanel Main;
    private JButton modifyCustomerIDButton;
    private JButton modifyCustomerNameButton;
    private JButton modifyCustomerEmailButton;
    private JButton modifyCustomerAddressButton;
    private JButton modifyCustomerNumberplateSButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton returnButton;
    private JTextField textField6;
    private JTextField textField7;
    private JButton modifyCarSButton;
    private JButton modifyFaxButton;
    private JTextField textField8;
    private JButton modifyDateIssuedButton;
    private JCheckBox regularCustomerCheckBox;
    private JCheckBox valuedCustomerCheckBox;
    private JTextField textField9;
    private JButton modifyDiscountButton;
    private static CustomerAccountModifierGUI j = new CustomerAccountModifierGUI();

    public CustomerAccountModifierGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                CustomerAccountMenuGUI.main();
            }
        });
    }

    public static void main(){
        j.setContentPane(new CustomerAccountModifierGUI().Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Making Account Changes");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}
