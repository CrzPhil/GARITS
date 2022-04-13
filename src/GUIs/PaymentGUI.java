package GUIs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;

import static java.lang.Thread.sleep;

public class PaymentGUI extends JFrame {
    private JPanel main;
    private JTextField numberField;
    private JTextField dateField;
    private JTextField nameField;
    private JTextField cvvField;
    private JButton closeButton;
    private JButton payButton;
    private static PaymentGUI j = new PaymentGUI();

    public PaymentGUI() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || (numberField.getText().isEmpty()) || (dateField.getText().isEmpty()) || (cvvField.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Required field missing.");
                } else if (numberField.getText().length() < 16) {
                    JOptionPane.showMessageDialog(null, "Card number too short.");
                } else if (dateField.getText().length() < 5) {
                    JOptionPane.showMessageDialog(null, "Date invalid.");
                } else if (cvvField.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "CVV invalid.");
                } else if (nameField.getText().length() < 5) {
                    JOptionPane.showMessageDialog(null, "Name too short.");
                } else {
                    JOptionPane.showMessageDialog(null, "Payment Successful!");
                    try {
                        sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    j.dispose();
                }

            }
        });
        numberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent g) {
                char a = g.getKeyChar();
                if (((a < '0') || (a > '9')) && (a != KeyEvent.VK_BACK_SPACE) && (a != '/') && (a != KeyEvent.VK_DELETE) && (a != KeyEvent.VK_ENTER)) {
                    g.consume();
                    JOptionPane.showMessageDialog(null, "Numbers only!");
                }
                if ((numberField.getText().length() > 15)) {
                    g.consume();
                    JOptionPane.showMessageDialog(null, "Card number too long!");
                }
            }
        });

        cvvField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent g) {
                char b = g.getKeyChar();
                if (((b < '0') || (b > '9')) && (b != KeyEvent.VK_BACK_SPACE) && (b != '/') && (b != KeyEvent.VK_DELETE) && (b != KeyEvent.VK_ENTER)) {
                    g.consume();
                    JOptionPane.showMessageDialog(null, "Numbers only!");
                }
                if ((cvvField.getText().length() > 2)) {
                    g.consume();
                    JOptionPane.showMessageDialog(null, "CVV too long!");
                }
            }
        });

        dateField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent g) {
                char c = g.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '/') && (c != KeyEvent.VK_DELETE) && (c != KeyEvent.VK_ENTER)) {
                    g.consume();
                    JOptionPane.showMessageDialog(null, "Invalid character.");
                }
                if ((dateField.getText().length() > 4)) {
                    g.consume();
                    JOptionPane.showMessageDialog(null, "Date too long!");
                }
            }
        });

        nameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent f) {
                char d = f.getKeyChar();
                if ((d == '¬') || (d == '`') || (d == '!') || (d == '"') || (d == '£') || (d == '$') || (d == '%') || (d == '^') || (d == '&') || (d == '*') || (d == '(') || (d == ')') || (d == '1') || (d == '_') || (d == '=') || (d == '+') || (d == '[') || (d == '{') || (d == ']') || (d == '}') || (d == ';') || (d == ':') || (d == '@') || (d == '#') || (d == '~') || (d == '|') || (d == ',') || (d == '<') || (d == '>') || (d == '/') || (d == '2') || (d == '3') || (d == '4') || (d == '5') || (d == '6') || (d == '7') || (d == '8') || (d == '9') || (d == '?')) {
                    f.consume();
                    JOptionPane.showMessageDialog(null, "Invalid character.");
                }
            }
        });
    }

    public static void main() {
        j.setContentPane(new PaymentGUI().main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Enter details to make payment.");
        j.setPreferredSize(new Dimension(800, 480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    private void createUIComponents() {
        payButton = new JButton();
        try {
            Image pImg = ImageIO.read(new FileInputStream("data/pay.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            payButton.setIcon(new ImageIcon(pImg));
        } catch (Exception ignored) {
        }
        payButton.setFocusPainted(false);

    }
}
