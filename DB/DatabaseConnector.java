package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import gradebookapp.Student;
import gradebookapp.Subject;

public class DatabaseConnector {

    private Connection connection;

    public void connect() throws SQLException {
        // Establish the database connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gradebook", "root", "");
    }

    public void close() throws SQLException {
        // Close the database connection
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public int insertStudent(Student student) throws SQLException {
        // Prepare the SQL statement for inserting student information
        String insertStudentQuery = "INSERT INTO students (name, matriculation, faculty, department, level, overall_gpa) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(insertStudentQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, student.getName());
        statement.setString(2, student.getMatriculationNumber());
        statement.setString(3, student.getFaculty());
        statement.setString(4, student.getDepartment());
        statement.setInt(5, student.getLevel());
        statement.setDouble(6, student.getOverallGPA());

        // Execute the insert statement
        statement.executeUpdate();

        // Get the auto-generated student ID
        ResultSet generatedKeys = statement.getGeneratedKeys();
        int studentId;
        if (generatedKeys.next()) {
            studentId = generatedKeys.getInt(1);
        } else {
            throw new SQLException("Failed to insert student. No student ID obtained.");
        }

        // Close the result set and statement
        generatedKeys.close();
        statement.close();

        return studentId;
    }

    public void insertSubject(int studentId, Subject subject) throws SQLException {
        // Prepare the SQL statement for inserting subject information
        String insertSubjectQuery = "INSERT INTO subjects (student_id, subject_name, grade, units) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSubjectQuery)) {
            statement.setInt(1, studentId);
            statement.setString(2, subject.getName());
            statement.setDouble(3, subject.getGrade());
            statement.setInt(4, subject.getUnits());

            // Execute the insert statement
            statement.executeUpdate();
        }
    }

    

    public void updateStudent(int studentId, Student updatedStudent) throws SQLException {
        // Prepare the SQL statement for updating student information
        String updateStudentQuery = "UPDATE students SET name = ?, matriculation = ?, faculty = ?, department = ?, level = ?, overall_gpa = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(updateStudentQuery);
        statement.setString(1, updatedStudent.getName());
        statement.setString(2, updatedStudent.getMatriculationNumber());
        statement.setString(3, updatedStudent.getFaculty());
        statement.setString(4, updatedStudent.getDepartment());
        statement.setInt(5, updatedStudent.getLevel());
        statement.setDouble(6, updatedStudent.getOverallGPA());
        statement.setInt(7, studentId);

        // Execute the update statement
        statement.executeUpdate();

        // Close the statement
        statement.close();
    }

    public void updateSubject(int subjectId, Subject updatedSubject) throws SQLException {
        // Prepare the SQL statement for updating subject information
        String updateSubjectQuery = "UPDATE subjects SET subject_naame = ?, grade = ?, units = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(updateSubjectQuery);
        statement.setString(1, updatedSubject.getName());
        statement.setDouble(2, updatedSubject.getGrade());
        statement.setInt(3, updatedSubject.getUnits());
        statement.setInt(4, subjectId);

        // Execute the update statement
        statement.executeUpdate();

        // Close the statement
        statement.close();
    }




    public ResultSet getAllStudents() throws SQLException {
        String query = "SELECT s.*, sub.subject_name, sub.grade, sub.units " +
                "FROM students s LEFT JOIN subjects sub ON s.id = sub.student_id";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}

