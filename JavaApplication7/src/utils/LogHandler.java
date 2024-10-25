/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import constants.Constants;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.Item;
import models.User;

/**
 *
 * @author Aorus
 */
public class LogHandler {

    FileManager fm = new FileManager(Constants.LOG_REPORT_PATH);
    User user;

    public LogHandler(User user) {
        this.user = user;
    }

    public long getCurrentUnixTimestamp() {
        return Instant.now().getEpochSecond();
    }

    public List<String> getAllLogActions() throws IOException {
        // Read the file content
        String fileContent = fm.readFile();

        // Split content into lines and convert to List
        return new ArrayList<>(Arrays.asList(fileContent.split("\n")));
    }

    public List<String> addLogActionToFile(String actions) throws IOException {
        List<String> allLogs = getAllLogActions();
        List<String> logs = new ArrayList<>(allLogs);

        // Create a new log entry based on the current action
        String timestamp = String.valueOf(getCurrentUnixTimestamp());
        String logEntry = String.join("|", user.getUsername(), actions, timestamp);
        logs.add(logEntry);

        // Join all log entries into a single string with newline as a separator
        String logsString = String.join("\n", logs);

        fm.writeFile(logsString);

        return logs;
    }
}
