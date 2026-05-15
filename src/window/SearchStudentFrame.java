package window;

import service.StudentService;

import javax.swing.*;
import java.awt.*;

public class SearchStudentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JTextField searchField;
    private JTextArea resultArea;

    public SearchStudentFrame() {
        setTitle("Search Student");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        topPanel.add(new JLabel("Enter Student Number or Name:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        StudentService service = new StudentService();
        resultArea.setText(service.searchStudent(searchField.getText()));
    }
}