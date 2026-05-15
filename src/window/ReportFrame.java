package window;

import service.StudentService;

import javax.swing.*;

public class ReportFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public ReportFrame() {
        setTitle("Report");
        setSize(400, 300);
        setLocationRelativeTo(null);

        StudentService service = new StudentService();

        JTextArea textArea = new JTextArea(service.generateReport());
        textArea.setEditable(false);

        add(new JScrollPane(textArea));
    }
}