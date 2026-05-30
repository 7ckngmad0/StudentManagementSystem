package model;

public class Grade {
    
    int studentId;
    String subject;
    double score;

    public Grade(int id, String sub, double sc) {
        this.studentId = id;
        this.subject = sub;
        this.score = sc;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }
}