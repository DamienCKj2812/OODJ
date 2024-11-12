/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Aorus
 */
public class StringFormatter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy").withZone(ZoneId.systemDefault());

    public static String formatUnixTimestamp(String timestamp) {
        try {
            long unixTime = Long.parseLong(timestamp); // Convert String to long
            return formatter.format(Instant.ofEpochSecond(unixTime));
        } catch (NumberFormatException e) {
            System.err.println("Invalid timestamp format: " + e.getMessage());
            return "Invalid date";
        }
    }
}
