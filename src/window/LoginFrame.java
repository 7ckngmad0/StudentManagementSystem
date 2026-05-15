package window;

import service.LoginService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        add(loginButton);
        add(exitButton);

        loginButton.addActionListener(e -> login());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void login() {
        LoginService service = new LoginService();

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (service.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new MainMenuFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }
}