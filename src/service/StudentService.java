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
        StringBuilder report = new StringBuilder();

        try {
            Connection conn = DBConnection.getConnection();

            // Total Students
            PreparedStatement ps1 =
                    conn.prepareStatement("SELECT COUNT(*) AS total FROM students");
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                report.append("Total Students: ")
                      .append(rs1.getInt("total"))
                      .append("\n\n");
            }

            // Students per Course
            report.append("Students Per Course:\n");

            PreparedStatement ps2 =
                    conn.prepareStatement(
                            "SELECT course, COUNT(*) AS total " +
                            "FROM students GROUP BY course");

            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                report.append(rs2.getString("course"))
                      .append(": ")
                      .append(rs2.getInt("total"))
                      .append("\n");
            }

            report.append("\n");

            // Students per Year Level
            report.append("Students Per Year Level:\n");

            PreparedStatement ps3 =
                    conn.prepareStatement(
                            "SELECT year_level, COUNT(*) AS total " +
                            "FROM students GROUP BY year_level");

            ResultSet rs3 = ps3.executeQuery();

            while (rs3.next()) {
                report.append("Year ")
                      .append(rs3.getInt("year_level"))
                      .append(": ")
                      .append(rs3.getInt("total"))
                      .append("\n");
            }

        } catch (Exception e) {
            return "Report Error: " + e.getMessage();
        }

        return report.toString();
    }
    public java.util.ArrayList<Object[]> getAllStudents() {
        java.util.ArrayList<Object[]> students = new java.util.ArrayList<>();

        String sql = "SELECT * FROM students";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("student_id"),
                    rs.getString("student_number"),
                    rs.getString("first_name") + " " + rs.getString("last_name"),
                    rs.getString("course"),
                    rs.getInt("year_level")
                };

                students.add(row);
            }

        } catch (Exception e) {
            System.out.println("Get Students Error: " + e.getMessage());
        }

        return students;
    }
    
    //get total students
    public int getTotalStudents() {
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "SELECT COUNT(*) AS total FROM students");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
            System.out.println("Count Error: " + e.getMessage());
        }

        return 0;
    }
    
    //get students per course
    public java.util.ArrayList<Object[]> getStudentsPerCourse() {
        java.util.ArrayList<Object[]> data = new java.util.ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "SELECT course, COUNT(*) AS total " +
                            "FROM students GROUP BY course");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getString("course"),
                        rs.getInt("total")
                });
            }

        } catch (Exception e) {
            System.out.println("Course Report Error: " + e.getMessage());
        }

        return data;
    }
    
    //get students per year
    public java.util.ArrayList<Object[]> getStudentsPerYearLevel() {
        java.util.ArrayList<Object[]> data = new java.util.ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "SELECT year_level, COUNT(*) AS total " +
                            "FROM students GROUP BY year_level");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getInt("year_level"),
                        rs.getInt("total")
                });
            }

        } catch (Exception e) {
            System.out.println("Year Report Error: " + e.getMessage());
        }

        return data;
    }
    
}