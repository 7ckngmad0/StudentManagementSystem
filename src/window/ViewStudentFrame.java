package window;

import service.StudentService;
import service.GradeService;
import model.Grade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewStudentFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public ViewStudentFrame() {
        setTitle("View Students");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("STUDENT LIST", SwingConstants.CENTER);
        header.setFont(new Font("ARIAL", Font.BOLD, 18));
        header.setBackground(new Color(30, 60, 114));
        header.setOpaque(true);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(255, 165, 0)),
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));
        add(header, BorderLayout.NORTH);

        String[] studentColumns = {"ID", "Student No", "Name", "Course", "Year"};
        DefaultTableModel studentModel = new DefaultTableModel(studentColumns, 0);
        JTable studentTable = new JTable(studentModel);

        StudentService studentService = new StudentService();
        for (Object[] row : studentService.getAllStudents()) {
            studentModel.addRow(row);
        }

        studentTable.getTableHeader().setBackground(new Color(220, 230, 245));
        studentTable.getTableHeader().setForeground(Color.BLACK);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        studentTable.setRowHeight(20);
        studentTable.setDefaultEditor(Object.class, null);

        String[] gradeColumns = {"Subject", "Grade"};
        DefaultTableModel gradeModel = new DefaultTableModel(gradeColumns, 0);
        JTable gradeTable = new JTable(gradeModel);
        gradeTable.setRowHeight(20);
        gradeTable.setDefaultEditor(Object.class, null);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(studentTable),
                new JScrollPane(gradeTable)
        );
        splitPane.setDividerLocation(250);
        add(splitPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton addGradeButton = new JButton("Add Grade");
        JButton refreshButton = new JButton("Refresh");

        buttons.add(addGradeButton);
        buttons.add(refreshButton);
        add(buttons, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentTable.getSelectedRow();

                if (selectedRow != -1) {
                    int studentId = Integer.parseInt(studentTable.getValueAt(selectedRow, 0).toString().trim());
                    gradeModel.setRowCount(0);

                    try {
                        GradeService gradeService = new GradeService();
                        ArrayList<Grade> grades = gradeService.getGrades(studentId);

                        for (Grade grade : grades) {
                            Object[] rowData = {
                                    grade.getSubject(),
                                    grade.getScore()
                            };
                            gradeModel.addRow(rowData);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error loading grades.");
                    }
                }
            }
        });

        addGradeButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();

            if (selectedRow != -1) {
                new AddGradeFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student first.");
            }
        });

        refreshButton.addActionListener(e -> {
            studentModel.setRowCount(0);

            for (Object[] row : new StudentService().getAllStudents()) {
                studentModel.addRow(row);
            }

            gradeModel.setRowCount(0);
        });

        setVisible(true);
    }
}