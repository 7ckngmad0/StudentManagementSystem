package window;

import model.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;

public class AddStudentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    private JTextField studentNumberField, firstNameField, lastNameField, courseField, yearField;

    public AddStudentFrame() {
        setTitle("Add Student");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        studentNumberField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        courseField = new JTextField();
        yearField = new JTextField();

        add(new JLabel("Student Number:"));
        add(studentNumberField);

        add(new JLabel("First Name:"));
        add(firstNameField);

        add(new JLabel("Last Name:"));
        add(lastNameField);

        add(new JLabel("Course:"));
        add(courseField);

        add(new JLabel("Year Level:"));
        add(yearField);

        JButton saveButton = new JButton("Save");
        JButton closeButton = new JButton("Close");

        add(saveButton);
        add(closeButton);

        saveButton.addActionListener(e -> saveStudent());
        closeButton.addActionListener(e -> dispose());
    }

    private void saveStudent() {
        try {
            String studentNumber = studentNumberField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String course = courseField.getText();
            int yearLevel = Integer.parseInt(yearField.getText());

            if (studentNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete all fields.");
                return;
            }

            Student student = new Student(studentNumber, firstName, lastName, course, yearLevel);
            StudentService service = new StudentService();

            if (service.addStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year level must be a number.");
        }
    }
}