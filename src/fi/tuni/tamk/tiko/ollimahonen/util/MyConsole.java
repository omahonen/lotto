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

}
