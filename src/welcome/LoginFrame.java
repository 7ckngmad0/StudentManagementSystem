package welcome;

import service.LoginService;
import model.User;

import javax.swing.*;
import java.awt.*;
import window.MainMenuFrame;
import window.TeacherMenuFrame;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(3, 3));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 5, 5));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        formPanel.add(loginButton);
        formPanel.add(exitButton);

        add(formPanel, BorderLayout.CENTER);

        formPanel.add(new JLabel("No Account? Register here:"));
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(0, 40));
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BorderLayout());
        registerPanel.add(registerButton, BorderLayout.CENTER);

        add(registerPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login());
        exitButton.addActionListener(e -> System.exit(0));
        registerButton.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            dispose();
        });
    }

    private void login() {
        LoginService service = new LoginService();

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = service.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            if (user.getRole().equalsIgnoreCase("Admin")) {
                new MainMenuFrame(user).setVisible(true);
            } else if (user.getRole().equalsIgnoreCase("Teacher")) {
                new TeacherMenuFrame(user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Unknown role: " + user.getRole());
                return;
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }
}