package window;

import service.StudentService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public ReportFrame() {
    	setTitle("View Students");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JLabel header = new JLabel("STUDENT MANAGEMENT REPORT", SwingConstants.CENTER);
        header.setFont(new Font("ARIAL", Font.BOLD, 18));
        header.setBackground(new Color(30, 60, 114));
        header.setOpaque(true);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(255, 165, 0)),
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));
        add(header, BorderLayout.NORTH);
        
        StudentService service = new StudentService();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        //students per course table
        JLabel courseLabel = new JLabel("Students Per Course");
        courseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        courseLabel.setForeground(new Color(30, 60, 114));
        panel.add(courseLabel);
        

        String[] courseColumns = {
                "Course",
                "Number of Students"
        };

        DefaultTableModel courseModel =
                new DefaultTableModel(courseColumns, 0);

        JTable courseTable = new JTable(courseModel);
        
        courseTable.getTableHeader().setBackground(new Color(220, 230, 245));
        courseTable.getTableHeader().setForeground(Color.BLACK);
        courseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        courseTable.setRowHeight(25);
        courseTable.setGridColor(new Color(200, 200, 200));
        courseTable.setSelectionBackground(new Color(30, 60, 114));
        courseTable.setSelectionForeground(Color.WHITE);
        courseTable.setDefaultEditor(Object.class, null);

        for (Object[] row : service.getStudentsPerCourse()) {
            courseModel.addRow(row);
        }
        
        //students per year table
        panel.add(new JScrollPane(courseTable));
        panel.add(Box.createVerticalStrut(15));
        
        JLabel yearLabel = new JLabel("Students Per Year Level");
        yearLabel.setFont(new Font("Arial", Font.BOLD, 16));
        yearLabel.setForeground(new Color(30, 60, 114));
        panel.add(yearLabel);

        String[] yearColumns = {
                "Year Level",
                "Number of Students"
        };

        DefaultTableModel yearModel =
                new DefaultTableModel(yearColumns, 0);

        JTable yearTable = new JTable(yearModel);
        yearTable.getTableHeader().setBackground(new Color(220, 230, 245));
        yearTable.getTableHeader().setForeground(Color.BLACK);
        yearTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        yearTable.setRowHeight(25);
        yearTable.setGridColor(new Color(200, 200, 200));
        yearTable.setSelectionBackground(new Color(30, 60, 114));
        yearTable.setSelectionForeground(Color.WHITE);
        yearTable.setDefaultEditor(Object.class, null);

        for (Object[] row : service.getStudentsPerYearLevel()) {
            yearModel.addRow(row);
        }

        panel.add(new JScrollPane(yearTable));
        
      //total students label
        JLabel totalLabel = new JLabel(
                "Total Students: " + service.getTotalStudents()
        );
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(30, 60, 114));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(totalLabel);
        panel.add(Box.createVerticalStrut(10));
        
        add(panel, BorderLayout.CENTER);
    }
}