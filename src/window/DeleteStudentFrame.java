package window;

import service.StudentService;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JTextField studentNumberField;

    public DeleteStudentFrame() {
        setTitle("Delete Student");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 10, 10));

        studentNumberField = new JTextField();

        add(new JLabel("Student Number:"));
        add(studentNumberField);

        JButton deleteButton = new JButton("Delete");
        JButton closeButton = new JButton("Close");

        add(deleteButton);
        add(closeButton);

        deleteButton.addActionListener(e -> deleteStudent());
        closeButton.addActionListener(e -> dispose());
    }

    private void deleteStudent() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?");

        if (confirm == JOptionPane.YES_OPTION) {
            StudentService service = new StudentService();

            if (service.deleteStudent(studentNumberField.getText())) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        }
    }
}