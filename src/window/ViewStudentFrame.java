package window;

import service.StudentService;

import javax.swing.*;

public class ViewStudentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public ViewStudentFrame() {
        setTitle("View Students");
        setSize(600, 400);
        setLocationRelativeTo(null);

        StudentService service = new StudentService();

        JTextArea textArea = new JTextArea(service.viewStudents());
        textArea.setEditable(false);

        add(new JScrollPane(textArea));
    }
}