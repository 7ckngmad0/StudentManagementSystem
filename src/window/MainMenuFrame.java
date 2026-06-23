package window;

import javax.swing.*;
import model.User;
import welcome.LoginFrame;
import java.awt.*;
import java.awt.event.*;

public class MainMenuFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private User loggedInUser;

    public MainMenuFrame(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        setTitle("Admin Menu - Student Record Management System");
        setSize(500, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainMenuPanel = new JPanel(new BorderLayout(10, 10)); // main panel
        mainMenuPanel.setBackground(new Color(245, 245, 250));
        mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + loggedInUser.getUsername() + "!",
                SwingConstants.CENTER);
        
        

        welcomeLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
        welcomeLabel.setForeground(new Color(28, 51, 94));

        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 0, 12)); // buttons
        buttonPanel.setBackground(new Color(245, 245, 250));

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton searchButton = new JButton("Search Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton reportButton = new JButton("Generate Report");
        JButton approveUsersButton = new JButton("Approve Accounts");
        JButton logoutButton = new JButton("Logout");

        JButton[] buttons = {addButton, viewButton, searchButton, updateButton, deleteButton, reportButton, approveUsersButton, logoutButton};

        Color normalBlue = new Color(28, 51, 94);
        Color normalRed = new Color(220, 53, 69);
        Color hoverYellow = new Color(255, 215, 0);

        for (JButton button : buttons) {

            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);

            if (button == logoutButton)
                button.setBackground(normalRed);
            else
                button.setBackground(normalBlue);

            button.addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e) {
                    button.setBackground(hoverYellow);
                    button.setForeground(Color.BLACK);
                }

                public void mouseExited(MouseEvent e) {
                    if (button == logoutButton)
                        button.setBackground(normalRed);
                    else
                        button.setBackground(normalBlue);

                    button.setForeground(Color.WHITE);
                }

            });

            buttonPanel.add(button);
        }

        mainMenuPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainMenuPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainMenuPanel);

        addButton.addActionListener(e -> new AddStudentFrame().setVisible(true));
        viewButton.addActionListener(e -> new ViewStudentFrame().setVisible(true));
        searchButton.addActionListener(e -> new SearchStudentFrame().setVisible(true));
        updateButton.addActionListener(e -> new UpdateStudentFrame().setVisible(true));
        deleteButton.addActionListener(e -> new DeleteStudentFrame().setVisible(true));
        reportButton.addActionListener(e -> new ReportFrame().setVisible(true));
        approveUsersButton.addActionListener(e -> new ApproveUsersFrame().setVisible(true));

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}