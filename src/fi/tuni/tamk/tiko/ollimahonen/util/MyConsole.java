package fi.tuni.tamk.tiko.ollimahonen.util;

import java.io.Console;

/**
 * Utility methods for requesting user input via the console interface.
 * 
 * Performs simple validation on the input to make sure it is of the appropriate
 * type.
 * 
 * @author Olli Mähönen
 */
public class MyConsole {

    /**
     * Attempts to read an integer value from the console. If the given value can
     * not be parsed as an integer, prints out an error message and tries again.
     * 
     * @param errorMessage Error message to be printed if console input is not an
     *                     integer.
     * @return The integer value read from the console.
     */
    public static int readInt(String errorMessage) {
        Console c = System.console();
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = Integer.parseInt(c.readLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    /**
     * Attempts to read an integer value from the console. The value must be
     * between tha min and max values provided, otherwise will display an error
     * message and try again. Will also display an error message if the input cannot
     * be parsed as an integer. Min and max values are inclusive. Min must be smaller than max.
     * 
     * @param min The minimum accepted value.
     * @param max The maximum accepted value.
     * @param errorMessageNonNumeric Error message that is diplayed if the input could not be parsed as in integer.
     * @param errorMessageNonMinAndMax Error message that is displayed if the input is not inside the specified range.
     * @return The integer value read from the console.
     */
    public static int readInt(int min, int max, String errorMessageNonNumeric, String errorMessageNonMinAndMax) {
        int value = 0;
        boolean validInput = false;
        while (!validInput) {
            value = readInt(errorMessageNonNumeric);
            if (value >= min && value <= max) {
                validInput = true;
            } else {
                System.out.println(errorMessageNonMinAndMax);
            }
        }
        return value;
    }

    /**
     * Attempts to read string input from console, and makes sure it matches one of the provided valid inputs.
     * Can ignore case if wanted. If input doesn't match, repeats message prompt and tries again.
     * @param prompt Text prompt that is shown to the user
     * @param validInputs List of valid options. If the list is empty, will accept any input.
     * @param ignoreCase Sets whether we want to ignore case or not
     * @return The validated user input. Note that this is not converted to upper- or lowercase.
     */
    public static String readString(String prompt, String[] validInputs, boolean ignoreCase) {
        String input = "";
        boolean matched = false;
        // Keep asking until we get a valid match
        while (!matched) {
            System.out.println(prompt + " " + java.util.Arrays.toString(validInputs));
            input = System.console().readLine();
            // Compare input to valid inputs. Ignore case if needed.
            for (int i = 0; i < validInputs.length && !matched; ++i) {
                if (ignoreCase ? input.equalsIgnoreCase(validInputs[i]) : input.equals(validInputs[i])) {
                    matched = true;
                }
            }
            // Guard against infinite loop if the list is empty.
            if (validInputs.length == 0) {
                matched = true;
            }
        }
        return input;
    }
}
