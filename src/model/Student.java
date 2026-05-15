package model;

public class Student extends Person {
    private int studentId;
    private String studentNumber;
    private String course;
    private int yearLevel;

    public Student() {
    }

    public Student(String studentNumber, String firstName, String lastName, String course, int yearLevel) {
        super(firstName, lastName);
        this.studentNumber = studentNumber;
        this.course = course;
        this.yearLevel = yearLevel;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
}