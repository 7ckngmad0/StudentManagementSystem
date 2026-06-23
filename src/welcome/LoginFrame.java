package welcome;

import service.LoginService;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import window.*;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("School Login");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(28, 51, 94));
        leftPanel.setPreferredSize(new Dimension(280, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        ImageIcon logoIcon = new ImageIcon("IMAGEpupbanner.png");
        
        Image resizedImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);

        JLabel logoLabel = new JLabel(new ImageIcon(resizedImg));
        
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel school1 = new JLabel("POLYTECHNIC");
        school1.setFont(new Font("Serif", Font.BOLD, 24));
        school1.setForeground(Color.WHITE);
        school1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel school2 = new JLabel("UNIVERSITY");
        school2.setFont(new Font("Serif", Font.BOLD, 24));
        school2.setForeground(Color.WHITE);
        school2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel school3 = new JLabel("OF THE PHILIPPINES");
        school3.setFont(new Font("Serif", Font.BOLD, 18));
        school3.setForeground(new Color(196, 160, 60));
        school3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel motto = new JLabel("Mula Sayo, Para sa Bayan");
        motto.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 18));
        motto.setForeground(Color.LIGHT_GRAY);
        motto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separator = new JSeparator(); //line
        separator.setMaximumSize(new Dimension(180, 2));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(school1);
        leftPanel.add(school2);
        leftPanel.add(school3);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(separator);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(motto);
        leftPanel.add(Box.createVerticalGlue());

        add(leftPanel, BorderLayout.WEST); // sa left side siya ilalagay

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(245, 245, 250));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(245, 245, 250));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("Welcome Back!");
        welcome.setFont(new Font("Serif", Font.BOLD, 30));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Please sign in to your account");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createVerticalStrut(30)); //para aligned sila vertically
        topPanel.add(welcome);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(subtitle);

        rightPanel.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(245, 245, 250));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 20, 50));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(350, 35));
        usernameField.setPreferredSize(new Dimension(350, 35));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(350, 35));
        passwordField.setPreferredSize(new Dimension(350, 35));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(userLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(usernameField);

        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(passLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(passwordField);

        formPanel.add(Box.createVerticalStrut(25));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(28, 51, 94));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(new Color(245, 245, 250));
        buttonPanel.setMaximumSize(new Dimension(350, 40));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        formPanel.add(buttonPanel);

        formPanel.add(Box.createVerticalStrut(20));

        JLabel registerLabel = new JLabel("No Account? Register here");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(registerLabel);

        formPanel.add(Box.createVerticalStrut(10));

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(196, 160, 60));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setMaximumSize(new Dimension(400, 55));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(registerButton);

        rightPanel.add(formPanel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.CENTER);

        //LOGIC NUNG NAKARAAN

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

                JOptionPane.showMessageDialog(
                        this,
                        "Unknown role: " + user.getRole()
                );
                return;
            }

            dispose();

        } else {

            String status = service.getAccountStatus(username, password);

            if (status != null &&
                    status.equalsIgnoreCase("Pending")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Your account is still pending admin approval."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password."
                );
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });

    }
}