package window;

import service.StudentService;
import javax.swing.*;
import java.awt.*; // pang font and color 
import javax.swing.table.*;

public class ViewStudentFrame extends JFrame {
	private static final long serialVersionUID = 1L;

    public ViewStudentFrame() {
        setTitle("View Students");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // kahit iclose mo gagana pa rin yung code mismo
        
        // HEADER
        JLabel viewStudentHeader = new JLabel("STUDENT LIST", SwingConstants.CENTER); // nakacenter
        viewStudentHeader.setFont(new Font("ARIAL", Font.BOLD, 18)); //font
        viewStudentHeader.setBackground(new Color(30, 60, 114)); // darkblue 
        viewStudentHeader.setOpaque(true); //set bg ng lbl, pag wala yung background mismo ng frame kukunin niya
        viewStudentHeader.setForeground(Color.WHITE); // text color
        viewStudentHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(255, 165, 0)), // orange yung baba parang combining tong compound border REQUIRED ng dalawang border, matte is matte 
                BorderFactory.createEmptyBorder(12, 10, 12, 10) // top left bottom right
            ));

        
        //for data
        String[] columns = {"ID", "Student No", "Name", "Course", "Year"}; // eto yung strings natin dun sa addstudent bale auwna lang yan
        DefaultTableModel studentiInfoTable= new DefaultTableModel(columns, 0); //default ayan yung class mismo, variablename, columns  yung headers natin tas magsisimula siya sa 0 rows kasi wala panag info
        
        //adding rows an data talaga si default model
        StudentService service = new StudentService();
        String[] rows = service.viewStudents().split("\n"); // kunin sa string ung viewstudents na method tas split para,, kuha siya student info pag nagadd ka, mag add muna tas check here
        for (int i = 2; i < rows.length; i++) { // ganito para di masama header and ung lines yung --- pero tsaka na palitan un, split kasi ung nakukuhang data dun ung nakaline pa siya eh so para mahiwalay hiwalay natin naka buong string yan sa memory
            if (!rows[i].trim().isEmpty()) { // skip blank or kapag walang nakalagay na lines, if hindi empty edi go
            	studentiInfoTable.addRow(rows[i].split("\\|")); // magadd na tayo sa row natin nung line of text na un and ireremove yung | para masplit siya into pieces sa columns natin
            }
        }
        
        //design jtable na toh kukunin niya yung data dito natin idedesin mismo yung table
        JTable viewStudentTable = new JTable(studentiInfoTable); //jtable need ipasok dito yun data since eto yung magdidisplay mismo ng table natin
        viewStudentTable.getTableHeader().setBackground(new Color(220, 230, 245)); 
        viewStudentTable.getTableHeader().setForeground(Color.BLACK); 
        viewStudentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        viewStudentTable.setRowHeight(20);
        viewStudentTable.setDefaultEditor(Object.class, null); //editor ng cells, all objects sa cells, null walang makakaedit
        
        add(viewStudentHeader, BorderLayout.NORTH);
        add(new JScrollPane(viewStudentTable), BorderLayout.CENTER); // bawal mo idiretso dito si studentinfo table kasi data lang siya si jscroll for visuals mga JTextArea JTable
    }
}