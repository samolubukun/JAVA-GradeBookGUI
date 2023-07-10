# JAVA-GradeBookGUI
A Java gradebook program with a GUI. It allows users to log in, enter student information and grades for multiple subjects, calculate the overall GPA,
and save the report to a file and a database. It consists of classes for the GUI, gradebook calculations, student information, subjects, and file handling. 
Update: Added MainmenuUI with gradebookapp, view, edit and delete buttons. Created a database using phpmyadmin (xampp server) called gradebook with two 
tables(refer to the "SQL- Database.txt" file in the repo). Added DatabaseConnector class for Database(My Sql) as well as the library for the mySQL connector, 
to connect to the database. Implemented Save to database(gradebook) in CalculateGPA() function to save user input in the database. Implemented viewRecords() to 
display student details(from the student table) in the database(gradebook). Updated viewRecords(): made it a scrollable window for viewing of the student records.
