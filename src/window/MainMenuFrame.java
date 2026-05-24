package window;

import javax.swing.*;

import welcome.LoginFrame;

import java.awt.*;

public class MainMenuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public MainMenuFrame() {
        setTitle("Student Record Management System");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton searchButton = new JButton("Search Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton reportButton = new JButton("Generate Report");
        JButton logoutButton = new JButton("Logout");

        add(addButton);
        add(viewButton);
        add(searchButton);
        add(updateButton);
        add(deleteButton);
        add(reportButton);
        add(logoutButton);

        addButton.addActionListener(e -> new AddStudentFrame().setVisible(true));
        viewButton.addActionListener(e -> new ViewStudentFrame().setVisible(true));
        searchButton.addActionListener(e -> new SearchStudentFrame().setVisible(true));
        updateButton.addActionListener(e -> new UpdateStudentFrame().setVisible(true));
        deleteButton.addActionListener(e -> new DeleteStudentFrame().setVisible(true));
        reportButton.addActionListener(e -> new ReportFrame().setVisible(true));

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}