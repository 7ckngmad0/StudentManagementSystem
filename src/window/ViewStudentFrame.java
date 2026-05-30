package window;

import service.StudentService;
import service.GradeService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import model.Grade;

public class ViewStudentFrame extends JFrame {

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

        String[] scols = {"ID", "Student No", "Name", "Course", "Year"};
        DefaultTableModel smodel = new DefaultTableModel(scols, 0);
        JTable stable = new JTable(smodel);

        StudentService ss = new StudentService();
        for (Object[] row : ss.getAllStudents()) {
            smodel.addRow(row);
        }

        stable.getTableHeader().setBackground(new Color(220, 230, 245));
        stable.getTableHeader().setForeground(Color.BLACK);
        stable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        stable.setRowHeight(20);
        stable.setDefaultEditor(Object.class, null);

        String[] gcols = {"Subject", "Grade"};
        DefaultTableModel gmodel = new DefaultTableModel(gcols, 0);
        JTable gtable = new JTable(gmodel);
        gtable.setRowHeight(20);
        gtable.setDefaultEditor(Object.class, null);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(stable), new JScrollPane(gtable));
        split.setDividerLocation(250);
        add(split, BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton addbtn = new JButton("Add Grade");
        JButton refbtn = new JButton("Refresh");
        btns.add(addbtn);
        btns.add(refbtn);
        add(btns, BorderLayout.SOUTH);

        stable.getSelectionModel().addListSelectionListener(e -> {
            int r = stable.getSelectedRow();
            if (r != -1) {
                int id = Integer.parseInt(stable.getValueAt(r, 0).toString().trim());
                gmodel.setRowCount(0);

                try {
                    GradeService gs = new GradeService();
                    ArrayList<Grade> list = gs.getGrades(id);

                    for (int i = 0; i < list.size(); i++) {
                        Grade g = list.get(i);
                        Object[] rowdata = { g.getSubject(), g.getScore() };
                        gmodel.addRow(rowdata);
                    }
                } catch (Exception ex) {
                    System.out.println("database error rip");
                }
            }
        });

        addbtn.addActionListener(e -> {
            int r = stable.getSelectedRow();
            if (r != -1) {
                new AddGradeFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "pick a student first dude");
            }
        });

        refbtn.addActionListener(e -> {
            smodel.setRowCount(0);
            for (Object[] row : new StudentService().getAllStudents()) {
                smodel.addRow(row);
            }
        });

        setVisible(true);
    }
}