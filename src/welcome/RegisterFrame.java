package welcome;

import javax.swing.*;
import service.RegisterService;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleBox;

    private final String[] roles = {"Admin", "Teacher"};

    public RegisterFrame() {

        setTitle("Register");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 250));

        JPanel headerPanel = new JPanel(new GridLayout(2, 1)); // title area
        headerPanel.setBackground(new Color(245, 245, 250));

        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(28, 51, 94));

        JLabel subtitle = new JLabel("Register New Account", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        headerPanel.add(title);
        headerPanel.add(subtitle);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15)); // form
        formPanel.setBackground(new Color(245, 245, 250));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        roleBox = new JComboBox<>(roles);
        roleBox.setFont(new Font("Arial", Font.PLAIN, 14));

        formPanel.add(userLabel);
        formPanel.add(usernameField);

        formPanel.add(passLabel);
        formPanel.add(passwordField);

        formPanel.add(confirmLabel);
        formPanel.add(confirmPasswordField);

        formPanel.add(roleLabel);
        formPanel.add(roleBox);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // buttons
        buttonPanel.setBackground(new Color(245, 245, 250));

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(28, 51, 94));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        registerButton.addActionListener(e -> register()); 
        exitButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }

    private void register() {

        RegisterService service = new RegisterService();

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        boolean success = service.register(username, password, role);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Registration submitted. Please wait for admin approval before logging in.");

            new LoginFrame().setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed.");
        }
    }
}