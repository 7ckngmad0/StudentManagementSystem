package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import service.GradeService;

public class AddGradeFrame extends JFrame {
    
    JTextField idf;
    JTextField subf;
    JTextField scoref;
    JButton btn;

    public AddGradeFrame() {
        setTitle("Add Grade");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Student ID:");
        l1.setBounds(30, 30, 80, 25);
        add(l1);

        idf = new JTextField();
        idf.setBounds(120, 30, 150, 25);
        add(idf);

        JLabel l2 = new JLabel("Subject:");
        l2.setBounds(30, 70, 80, 25);
        add(l2);

        subf = new JTextField();
        subf.setBounds(120, 70, 150, 25);
        add(subf);

        JLabel l3 = new JLabel("Grade:");
        l3.setBounds(30, 110, 80, 25);
        add(l3);

        scoref = new JTextField();
        scoref.setBounds(120, 110, 150, 25);
        add(scoref);

        btn = new JButton("Save");
        btn.setBounds(120, 160, 100, 25);
        add(btn);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String idt = idf.getText();
                String subt = subf.getText();
                String scoret = scoref.getText();

                if (idt.equals("") || subt.equals("") || scoret.equals("")) {
                    JOptionPane.showMessageDialog(null, "fill up everything dude");
                    return;
                }

                boolean badId = false;
                for (int i = 0; i < idt.length(); i++) {
                    char c = idt.charAt(i);
                    if (c < '0' || c > '9') {
                        badId = true;
                    }
                }

                if (badId == true) {
                    JOptionPane.showMessageDialog(null, "id should be numbers only");
                    return;
                }

                boolean badScore = false;
                for (int i = 0; i < scoret.length(); i++) {
                    char c = scoret.charAt(i);
                    if ((c < '0' || c > '9') && c != '.') {
                        badScore = true;
                    }
                }

                if (badScore == true) {
                    JOptionPane.showMessageDialog(null, "wrong grade format");
                    return;
                }

                int id = Integer.parseInt(idt);
                double score = Double.parseDouble(scoret);

                GradeService s = new GradeService();
                boolean res = false;
                
                try {
                    res = s.saveGrade(id, subt, score);
                } catch (Exception ex) {
                    System.out.println("error saving");
                }

                if (res == true) {
                    JOptionPane.showMessageDialog(null, "grade saved");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "didnt save");
                }
            }
        });
    }
}