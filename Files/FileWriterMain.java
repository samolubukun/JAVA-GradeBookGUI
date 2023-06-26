package Files;

import java.io.*;


public class FileWriterMain {

    public static void main(String[] args) throws IOException {
        FileWriterMain fileWriterMain = new FileWriterMain();
        fileWriterMain.appendData("");
    }

    public static void appendData(String data) throws IOException {
        File myFile = new File("student_records.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(myFile, true);  // Set the second parameter to true for append mode
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        printWriter.println(data);
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }
}


