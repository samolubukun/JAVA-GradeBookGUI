package gradebookapp;

public class Subject {
    private String name;
    private double grade;
    private int units;
    private String gradeLetter;
    private double gpa;

    public Subject(String name, double grade, int units) {
        this.name = name;
        this.grade = grade;
        this.units = units;
        calculateGradeLetter();
        calculateGPA();
    }

    private void calculateGradeLetter() {
        if (grade >= 70) {
            gradeLetter = "A";
        } else if (grade >= 60) {
            gradeLetter = "B";
        } else if (grade >= 50) {
            gradeLetter = "C";
        } else if (grade >= 40) {
            gradeLetter = "D";
        } else {
            gradeLetter = "F";
        }
    }

    private void calculateGPA() {
        switch (gradeLetter) {
            case "A":
                gpa = 5.0;
                break;
            case "B":
                gpa = 4.0;
                break;
            case "C":
                gpa = 3.0;
                break;
            case "D":
                gpa = 2.0;
                break;
            default:
                gpa = 1.0;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public int getUnits() {
        return units;
    }

    public String getGradeLetter() {
        return gradeLetter;
    }

    public double getGPA() {
        return gpa;
    }
}

