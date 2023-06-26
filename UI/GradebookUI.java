package UI;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import gradebookapp.Student;
import gradebookapp.Gradebook;
import gradebookapp.Subject;
import Files.FileWriterMain;

public class GradebookUI {

    private static int NUM_SUBJECTS = 0;

    public static void main(String[] args) {
        GradebookUI gradeui= new GradebookUI();
        gradeui.LoginUI();
    }

    JFrame frame;
    JButton loginButton = new JButton("Login");
    JLabel nameLabel = new JLabel("Username:");
    JTextField nameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel passwordLabel = new JLabel("Password:");

    public void LoginUI() {
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        nameLabel.setBounds(10, 10, 100, 20);
        frame.getContentPane().add(nameLabel);

        nameField.setBounds(120, 10, 150, 20);
        frame.getContentPane().add(nameField);

        passwordLabel.setBounds(10, 40, 100, 20);
        frame.getContentPane().add(passwordLabel);

        passwordField.setBounds(120, 40, 150, 20);
        frame.getContentPane().add(passwordField);

        loginButton.setBounds(100, 80, 100, 30);
        frame.getContentPane().add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("adminpass")) {
                    frame.dispose();
                    GradebookWindowUI();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    JFrame gradebookframe = new JFrame("Student Gradebook");
    JLabel nameLabel2 = new JLabel("Name:");
    JTextField nameField2 = new JTextField();
    JLabel matriculationLabel = new JLabel("Matriculation Number:");
    JTextField matriculationField = new JTextField();
    JLabel facultyLabel = new JLabel("Faculty:");
    JTextField facultyField = new JTextField();
    JLabel departmentLabel = new JLabel("Department:");
    JTextField departmentField = new JTextField();
    JLabel levelLabel = new JLabel("Level:");
    JTextField levelField = new JTextField();
    JTextField[] subjectFields;
    JTextField[] gradeFields;
    JTextField[] unitFields;
    JTextArea reportTextArea = new JTextArea();
    JLabel subjectLabel = new JLabel("Subject");
    JLabel gradeLabel = new JLabel("Grade");
    JLabel unitLabel = new JLabel("Units");
    JButton calculateButton = new JButton("Calculate GPA");
    JButton saveButton = new JButton("Save Report");

    public void GradebookWindowUI() {
        gradebookframe.setBounds(100, 100, 400, 600);
        gradebookframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradebookframe.getContentPane().setLayout(null);

        nameLabel2.setBounds(10, 10, 100, 20);
        gradebookframe.getContentPane().add(nameLabel2);

        nameField2.setBounds(120, 10, 100, 20);
        gradebookframe.getContentPane().add(nameField2);

        matriculationLabel.setBounds(10, 40, 150, 20);
        gradebookframe.getContentPane().add(matriculationLabel);

        matriculationField.setBounds(160, 40, 100, 20);
        gradebookframe.getContentPane().add(matriculationField);

        facultyLabel.setBounds(10, 70, 100, 20);
        gradebookframe.getContentPane().add(facultyLabel);

        facultyField.setBounds(120, 70, 100, 20);
        gradebookframe.getContentPane().add(facultyField);

        departmentLabel.setBounds(10, 100, 100, 20);
        gradebookframe.getContentPane().add(departmentLabel);

        departmentField.setBounds(120, 100, 100, 20);
        gradebookframe.getContentPane().add(departmentField);

        levelLabel.setBounds(10, 130, 100, 20);
        gradebookframe.getContentPane().add(levelLabel);

        levelField.setBounds(120, 130, 60, 20);
        gradebookframe.getContentPane().add(levelField);

        subjectLabel.setBounds(10, 160, 100, 20);
        gradebookframe.getContentPane().add(subjectLabel);

        gradeLabel.setBounds(120, 160, 100, 20);
        gradebookframe.getContentPane().add(gradeLabel);

        unitLabel.setBounds(230, 160, 100, 20);
        gradebookframe.getContentPane().add(unitLabel);

        String numSubjectsStr = JOptionPane.showInputDialog(frame, "Enter the number of subjects:");
        try {
            NUM_SUBJECTS = Integer.parseInt(numSubjectsStr);
        } catch (NumberFormatException e) {
            NUM_SUBJECTS = 0;
        }

        subjectFields = new JTextField[NUM_SUBJECTS];
        gradeFields = new JTextField[NUM_SUBJECTS];
        unitFields = new JTextField[NUM_SUBJECTS];

        int startY = 180;
        int yGap = 30;
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            int yPos = startY + i * yGap;

            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(10, yPos, 100, 20);
            gradebookframe.getContentPane().add(subjectFields[i]);

            gradeFields[i] = new JTextField();
            gradeFields[i].setBounds(120, yPos, 50, 20);
            gradebookframe.getContentPane().add(gradeFields[i]);

            unitFields[i] = new JTextField();
            unitFields[i].setBounds(230, yPos, 50, 20);
            gradebookframe.getContentPane().add(unitFields[i]);
        }


        calculateButton.setBounds(10, startY + NUM_SUBJECTS * yGap, 150, 30);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGPA();
            }
        });
        gradebookframe.getContentPane().add(calculateButton);

        reportTextArea = new JTextArea();
        reportTextArea.setBounds(10, startY + NUM_SUBJECTS * yGap + 40, 360, 250);
        reportTextArea.setEditable(false);
        gradebookframe.getContentPane().add(reportTextArea);


        saveButton.setBounds(200, startY + NUM_SUBJECTS * yGap, 150, 30);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveReport();
            }
        });
        gradebookframe.getContentPane().add(saveButton);

        gradebookframe.setVisible(true);
    }

    public void calculateGPA() {
        try {
            String name = nameField2.getText();
            String matriculation = matriculationField.getText();
            String faculty = facultyField.getText();
            String department = departmentField.getText();
            int level = Integer.parseInt(levelField.getText());

            Student student = new Student(name, matriculation, faculty, department, level);

            for (int i = 0; i < NUM_SUBJECTS; i++) {
                String subject = subjectFields[i].getText();
                double grade = Double.parseDouble(gradeFields[i].getText());
                int units = Integer.parseInt(unitFields[i].getText());

                if (grade > 100) {
                    JOptionPane.showMessageDialog(frame, "Grade higher than 100 entered for subject " + subject + "!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                student.addSubject(subject, grade, units);
            }

            double overallGPA = Gradebook.calculateOverallGPA(student);
            student.setOverallGPA(overallGPA);

            reportTextArea.setText("");
            reportTextArea.append("Name: " + student.getName() + "\n");
            reportTextArea.append("Matriculation Number: " + student.getMatriculationNumber() + "\n");
            reportTextArea.append("Faculty: " + student.getFaculty() + "\n");
            reportTextArea.append("Department: " + student.getDepartment() + "\n");
            reportTextArea.append("Level: " + student.getLevel() + "\n");
            reportTextArea.append("\nSubject\tGrade\tUnits\tGPA\n");

            for (Subject subject : student.getSubjects()) {
                reportTextArea.append(subject.getName() + "\t" + subject.getGrade() + "\t" +
                        subject.getUnits() + "\t" + subject.getGradeLetter() + " (" + subject.getGPA() + ")\n");
            }

            reportTextArea.append("\nOverall GPA: " + student.getOverallGPA());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void saveReport() {
        try {
            String reportText = reportTextArea.getText();
            FileWriterMain.appendData(reportText);

            nameField2.setText("");
            matriculationField.setText("");
            facultyField.setText("");
            departmentField.setText("");
            levelField.setText("");

            for (int i = 0; i < NUM_SUBJECTS; i++) {
                subjectFields[i].setText("");
                gradeFields[i].setText("");
                unitFields[i].setText("");
            }
            reportTextArea.setText("");

            JOptionPane.showMessageDialog(frame, "Report saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while saving the report!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
