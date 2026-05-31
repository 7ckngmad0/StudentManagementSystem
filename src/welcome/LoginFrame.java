package welcome;

import service.LoginService;
import model.User;

import javax.swing.*;
import java.awt.*;
import window.*;

//login window
public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L; //serialization of JFrame

    //input fields and passwords
    private JTextField usernameField;
    private JPasswordField passwordField;

    //constructor pang build ng GUI ng login
    public LoginFrame() {
        setTitle("Login"); //title
        setSize(400, 270); //window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close application when X button is clicked
        setLocationRelativeTo(null); //center window ng screen

        setLayout(new BorderLayout(3, 3)); //main layout

        //panel ng login components
        JPanel formPanel = new JPanel(); 
        formPanel.setLayout(new GridLayout(4, 2, 5, 5));

        
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

      //password label and text field
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        //buttons
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        //aadd sya sa window
        formPanel.add(loginButton);
        formPanel.add(exitButton);

        //iaadd nya yung login form panel sa center ng frame
        add(formPanel, BorderLayout.CENTER);

        formPanel.add(new JLabel("No Account? Register here:"));
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(0, 40));
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BorderLayout());
        registerPanel.add(registerButton, BorderLayout.CENTER);

        //iaadd nya sa baba
        add(registerPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login()); //cacall nya yung login pag clinick yung login
        exitButton.addActionListener(e -> System.exit(0)); //closes app when clicked
        registerButton.addActionListener(e -> { //opens register frame when clicked
            new RegisterFrame().setVisible(true);
            dispose();
        });
    }

    //login process
    private void login() {
        LoginService service = new LoginService(); //handles authentication

        //get username and pass entered
        String username = usernameField.getText(); 
        String password = new String(passwordField.getPassword());

        User user = service.login(username, password); //verify credentials

        //successful login
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            //pag admin role
            if (user.getRole().equalsIgnoreCase("Admin")) {
                new MainMenuFrame(user).setVisible(true);
            } 
            //pag teacher role
            else if (user.getRole().equalsIgnoreCase("Teacher")) {
                new TeacherMenuFrame(user).setVisible(true);
            } 
            //unrecognized role
            else {
                JOptionPane.showMessageDialog(this, "Unknown role: " + user.getRole());
                return;
            }

            dispose(); //closes login window if successful
        }else {
            String status = service.getAccountStatus(username, password); //checks kung for approval pa

            if (status != null && status.equalsIgnoreCase("Pending")) {
                JOptionPane.showMessageDialog(this, "Your account is still pending admin approval.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
    }
}