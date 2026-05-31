package welcome;

import javax.swing.*;

import service.RegisterService;

import java.awt.*;

public class RegisterFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleBox;
    private final String[] roles = {"Admin", "Teacher"};
	
	public RegisterFrame() {
		setTitle("Register");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));
        
        add (new JLabel("Username: "));
        usernameField = new JTextField();
        add(usernameField);
        
        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);
        
        add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);
        
        roleBox = new JComboBox<>(roles);
        add(new JLabel("Roles:"));
        add(roleBox);
        
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");
        
        add(registerButton);
        add(exitButton);
        
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
	    	
	    	if(success) {
	    		JOptionPane.showMessageDialog(this, "Account Registered Successfully!");
	    		new LoginFrame().setVisible(true);
	    		dispose();
	    	} else {
	    		JOptionPane.showMessageDialog(this, "Registration Failed.");
	    	}
	}
}
