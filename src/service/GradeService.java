package service;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Grade;

public class GradeService {
    
    public boolean saveGrade(int id, String sub, double sc) throws Exception {
        
        String q = "INSERT INTO grades (student_id, subject_name, grade_value) VALUES (?, ?, ?)";
        
        Connection c = DBConnection.getConnection();
        PreparedStatement p = c.prepareStatement(q);
        
        p.setInt(1, id);
        p.setString(2, sub);
        p.setDouble(3, sc);
        
        int rows = p.executeUpdate();
        
        if (rows > 0) {
            return true;
        }
        
        return false;
    }

    public ArrayList<Grade> getGrades(int id) throws Exception {
        
        ArrayList<Grade> list = new ArrayList<>();
        String q = "SELECT subject_name, grade_value FROM grades WHERE student_id = ?";
        
        Connection c = DBConnection.getConnection();
        PreparedStatement p = c.prepareStatement(q);
        
        p.setInt(1, id);
        
        ResultSet r = p.executeQuery();
        
        while (r.next()) {
            String sub = r.getString("subject_name");
            double sc = r.getDouble("grade_value");
            
            Grade g = new Grade(id, sub, sc);
            list.add(g);
        }
        
        return list;
    }
}