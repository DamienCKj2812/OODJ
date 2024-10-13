package utils;

public class InputValidator {

    /**
     * Checks if the given input is empty or null.
     *
     * @param input the input string to check
     * @param fieldName the name of the field (for error messages)
     * @throws IllegalArgumentException if the input is empty or null
     */
    
    public static void validateNotEmpty(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }
}
