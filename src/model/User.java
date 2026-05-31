package model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private String status;

    public User() {
    }

    public User(int userId, String username, String password, String role) {
        this(userId, username, password, role, "Approved");
    }

    public User(int userId, String username, String password, String role, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}