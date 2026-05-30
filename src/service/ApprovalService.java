package service;

import database.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class ApprovalService {
	
	//kukunin nya lahat ng mga pending na accounts na need ng admin approval

    public ArrayList<User> getPendingUsers() {
        ArrayList<User> users = new ArrayList<>(); //dito nya sstore yung mga pending users
        String sql = "SELECT * FROM users WHERE status = 'Pending' ORDER BY user_id"; //sql query pang get ng mga pending accounts

        try {
            Connection conn = DBConnection.getConnection(); //pang connect sa database
            PreparedStatement ps = conn.prepareStatement(sql); //pang prepare ng sql statement
            ResultSet rs = ps.executeQuery(); //ieexecute yung SELECT na query

            //mag lloop sya sa lahat ng records na nireturn ng query
            while (rs.next()) { 
                users.add(new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Get Pending Users Error: " + e.getMessage()); //mag rrun sya pag may error
        }

        return users; //rereturn nya yung list ng pending users
    }

    public boolean approveUser(int userId) {
        String sql = "UPDATE users SET status = 'Approved' WHERE user_id = ?"; //update user status

        try {
            Connection conn = DBConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, userId); //rereplace nya yung '?' ng ID ng user
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Approve User Error: " + e.getMessage());
            return false;
        }
    }

    //reject and remove pending acc sa database
    public boolean rejectUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ? AND status = 'Pending'"; //dedelete nya lang yung account na pending

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Reject User Error: " + e.getMessage());
            return false;
        }
    }
}