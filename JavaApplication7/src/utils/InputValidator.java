package utils;

public class InputValidator {

    //Checks if the given input is empty or null.
    public static void validateNotEmpty(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    // Validates if the input is a numeric value.
    public static void validateIsNumber(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }

        // Check if input contains a decimal point
        if (!input.contains(".")) {
            throw new IllegalArgumentException(fieldName + " must be a decimal number.");
        }

        try {
            Double.parseDouble(input); // Tries to parse input as a double
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

    // Validates if the input is an integer value.
    public static void validateIsInteger(String input, String fieldName) {
        try {
            Integer.parseInt(input); // Tries to parse input as an integer
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be an integer.");
        }
    }
}
