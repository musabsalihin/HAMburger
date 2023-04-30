import javax.swing.*;

public class GUI {
    protected static JFrame frame;
    protected static JFrame frameHome;
    private static JPanel panel1;
    private static JLabel userLabel;
    private static JLabel pwLabel;
    private static JButton loginB;
    protected static JTextField userField;
    protected static JPasswordField pwField;
    protected static JLabel successful;

    public void Open() {
        frame = new JFrame("HAMburger",null);
        panel1 = new JPanel();
        userLabel = new JLabel("Username");
        pwLabel = new JLabel("Password");
        loginB = new JButton("Login");
        userField = new JTextField();
        pwField = new JPasswordField();
        successful = new JLabel("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 200);
        frame.setLocationRelativeTo(null);
        
        panel1.setLayout(null);
        frame.add(panel1);
        userLabel.setBounds(20, 10, 80, 20);
        userField.setBounds(120, 10, 200, 20);
        pwLabel.setBounds(20, 40, 100, 20);
        pwField.setBounds(120, 40, 200, 20);
        loginB.setBounds(259, 70, 70, 20);
        loginB.addActionListener(new home());
        successful.setBounds(20,90,300,20);
        panel1.add(userLabel);
        panel1.add(userField);
        panel1.add(pwLabel);
        panel1.add(pwField);
        panel1.add(loginB);

        frame.setVisible(true);
    }
}
