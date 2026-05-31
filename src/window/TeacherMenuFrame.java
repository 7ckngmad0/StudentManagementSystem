package window;

import javax.swing.*;

import model.User;
import welcome.LoginFrame;

import java.awt.*;

public class TeacherMenuFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private User loggedInUser;

    public TeacherMenuFrame(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        setTitle("Teacher Menu - Student Record Management System");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, Professor " + loggedInUser.getUsername() + "!", SwingConstants.CENTER);
        JButton viewButton = new JButton("View Students");
        JButton searchButton = new JButton("Search Student");
        JButton reportButton = new JButton("Generate Report");
        JButton logoutButton = new JButton("Logout");

        add(welcomeLabel);
        add(viewButton);
        add(searchButton);
        add(reportButton);
        add(logoutButton);

        viewButton.addActionListener(e -> new ViewStudentFrame().setVisible(true));
        searchButton.addActionListener(e -> new SearchStudentFrame().setVisible(true));
        reportButton.addActionListener(e -> new ReportFrame().setVisible(true));

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}