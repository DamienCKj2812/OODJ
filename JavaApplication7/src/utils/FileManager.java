package utils;

import java.io.*;
import java.nio.file.*;

public class FileManager {

    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    // Getter and Setter for filePath
    public String getFilePath() {
        return filePath;
    }

    // Debug perpose
    public String getAbsolutePath() {
        return new File(filePath).getAbsolutePath();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

   public String readFile() throws IOException {
        File inputFile = new File(filePath);
        if (!inputFile.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n"); // Read lines and append newline
        }
        reader.close();
        return content.toString().trim(); // Remove the last newline for processing
    }

    // Method to write content to a text file (overwrites existing content)
    public void writeFile(String content) throws IOException {
        // Write content to the file, overwriting existing content
        Files.write(Paths.get(filePath), content.getBytes(), 
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }


    // Method to delete the text file
    public boolean deleteFile() throws IOException {
        return Files.deleteIfExists(Paths.get(filePath));
    }

    // Method to check if the file exists
    public boolean isFileExists() {
        return Files.exists(Paths.get(filePath));
    }

}
