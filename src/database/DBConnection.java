package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_record_db";
    private static final String USER = "INPUT_YOUR_USERNAME_HERE";
    private static final String PASSWORD = "INPUT_YOUR_PASSWORD_HERE";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Database Connection Error: " + e.getMessage());
            return null;
        }
    }
}
