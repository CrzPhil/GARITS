import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditVehicleGUI extends JFrame{
    private JPanel main;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton saveChangesButton;
    private JButton discardButton;
    private static EditVehicleGUI j = new EditVehicleGUI();

    public EditVehicleGUI() {
        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });
    }
    public static void main(){
        j.setContentPane(new EditVehicleGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Edit Vehicle Details");
        j.setPreferredSize(new Dimension(800,600));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);

    }
}
