package gradebookapp;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String matriculationNumber;
    private String faculty;
    private String department;
    private int level;
    private List<Subject> subjects;
    private double overallGPA;

    public Student(String name, String matriculationNumber, String faculty, String department, int level) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
        this.faculty = faculty;
        this.department = department;
        this.level = level;
        subjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addSubject(String name, double grade, int units) {
        Subject subject = new Subject(name, grade, units);
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setOverallGPA(double overallGPA) {
        this.overallGPA = overallGPA;
    }

    public double getOverallGPA() {
        return overallGPA;
    }
}
