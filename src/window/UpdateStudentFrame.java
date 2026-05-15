package window;

import model.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JTextField studentNumberField, firstNameField, lastNameField, courseField, yearField;

    public UpdateStudentFrame() {
        setTitle("Update Student");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        studentNumberField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        courseField = new JTextField();
        yearField = new JTextField();

        add(new JLabel("Student Number to Update:"));
        add(studentNumberField);

        add(new JLabel("New First Name:"));
        add(firstNameField);

        add(new JLabel("New Last Name:"));
        add(lastNameField);

        add(new JLabel("New Course:"));
        add(courseField);

        add(new JLabel("New Year Level:"));
        add(yearField);

        JButton updateButton = new JButton("Update");
        JButton closeButton = new JButton("Close");

        add(updateButton);
        add(closeButton);

        updateButton.addActionListener(e -> updateStudent());
        closeButton.addActionListener(e -> dispose());
    }

    private void updateStudent() {
        try {
            Student student = new Student(
                    studentNumberField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    courseField.getText(),
                    Integer.parseInt(yearField.getText())
            );

            StudentService service = new StudentService();

            if (service.updateStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year level must be a number.");
        }
    }
}