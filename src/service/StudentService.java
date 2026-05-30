package service;

import database.DBConnection;
import model.Student;

import java.sql.*;

public class StudentService {
	
	//adds new student to the database
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_number, first_name, last_name, course, year_level) VALUES (?, ?, ?, ?, ?)"; //sql query para mag insert ng student

        try {
            Connection conn = DBConnection.getConnection(); //pang connect sa database
            PreparedStatement ps = conn.prepareStatement(sql); //prepare sql statement
            
            //set values from student object to sql query
            ps.setString(1, student.getStudentNumber());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getCourse());
            ps.setInt(5, student.getYearLevel());

            //execute the query and only returns true pag may naadd na row
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Add Student Error: " + e.getMessage());
            return false;
        }
    }

    //display lahat ng students
    public String viewStudents() {
        String sql = "SELECT * FROM students"; //gets all students
        StringBuilder result = new StringBuilder(); //yung StringBuilder pang gawa ng formatted output string

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //heather
            result.append("ID | Student No | Name | Course | Year\n");
            result.append("-----------------------------------------\n");

            //loops sa lahat ng student record
            while (rs.next()) {
                result.append(rs.getInt("student_id")).append(" | ")
                        .append(rs.getString("student_number")).append(" | ")
                        .append(rs.getString("first_name")).append(" ")
                        .append(rs.getString("last_name")).append(" | ")
                        .append(rs.getString("course")).append(" | ")
                        .append(rs.getInt("year_level")).append("\n");
            }

        } catch (Exception e) {
            return "View Students Error: " + e.getMessage();
        }

        return result.toString(); //returns the formatted list
    }

    public String searchStudent(String keyword) {
        String sql = "SELECT * FROM students WHERE student_number = ? OR first_name LIKE ? OR last_name LIKE ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, keyword); //exact match ng student number
            ps.setString(2, "%" + keyword + "%"); //partial match ng first at last names
            ps.setString(3, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            //pag nakita na nya yung student
            if (rs.next()) { 
                return "Student ID: " + rs.getInt("student_id") +
                        "\nStudent Number: " + rs.getString("student_number") +
                        "\nName: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                        "\nCourse: " + rs.getString("course") +
                        "\nYear Level: " + rs.getInt("year_level");
            }

        } catch (Exception e) {
            return "Search Error: " + e.getMessage();
        }

        //pag walang nakita
        return "Student not found.";
    }

    //update student information
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET first_name=?, last_name=?, course=?, year_level=? WHERE student_number=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            //update values
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getCourse());
            ps.setInt(4, student.getYearLevel());
            ps.setString(5, student.getStudentNumber()); 

            return ps.executeUpdate() > 0; //mag uupdate na sya

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
            return false;
        }
    }

    //delete record
    public boolean deleteStudent(String studentNumber) {
        String sql = "DELETE FROM students WHERE student_number=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, studentNumber); //set nya yung student record na idedelete

            return ps.executeUpdate() > 0; //execute

        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
            return false;
        }
    }

    //student report
    public String generateReport() {
        String sql = "SELECT COUNT(*) AS total FROM students"; //counts all rows in the table

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return "Student Report\n\nTotal Students: " + rs.getInt("total"); //kukunin nya yung total number ng students
            }

        } catch (Exception e) {
            return "Report Error: " + e.getMessage();
        }

        return "No report generated.";
    }
}