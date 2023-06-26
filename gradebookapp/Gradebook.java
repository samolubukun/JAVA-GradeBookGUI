package gradebookapp;

public class Gradebook {
    public static double calculateOverallGPA(Student student) {
        double totalCredits = 0.0;
        double weightedCredits = 0.0;

        for (Subject subject : student.getSubjects()) {
            totalCredits += subject.getUnits();
            weightedCredits += subject.getUnits() * subject.getGPA();
        }

        return weightedCredits / totalCredits;
    }
}

