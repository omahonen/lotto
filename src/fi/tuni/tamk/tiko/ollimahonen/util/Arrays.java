package fi.tuni.tamk.tiko.ollimahonen.util;

/**
 * Class for creating, manipulating and converting arrays
 * 
 * @author Olli Mähönen
 */

public class Arrays {

    /**
     * Converts an array of strings into an array of integers.
     * 
     * Attempts to convert the values represented by the strings to equivalent integers, and returns them in an array of the same size. Throws an exception if a string can not be parsed as an integer.
     * 
     * @param array Array of strings that will be converted.
     * @return Array of integers containing the converted values.
     */
    public static int[] toIntArray(String[] array) {
        int [] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.parseInt(array[i]);
        }
        return result;
    }


    /**
     * Checks if an array of integers contains a value.
     * 
     * Returns true if the value is found at least once in the array.
     * 
     * @param value The value we are looking for
     * @param array The array where we look for the value
     * @return True if the value is found inside the array
     */
    public static boolean contains(int value, int[] array) {
        boolean found = false;
        // Loop through array, stop if value found.
        for (int i = 0; i < array.length && !found; i++) {
            if (array[i] == value) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Compares the contents of two arrays and returns the amount of identical values between them.
     * 
     * Values must be sorted low to high. Arrays values do not need to be unique.
     * 
     * @param array1 First array
     * @param array2 Second array
     * @return The amount of values that are same between the arrays.
     */
    public static int containsSameValues(int[] array1, int[] array2) {
        int sameValues = 0;
        int array1Pos = 0;
        int array2Pos = 0;

        // Step through the arrays until either one is exhausted. Requires that the values are in low to high order.
        while (array1Pos < array1.length && array2Pos < array2.length) {
            if (array1[array1Pos] > array2[array2Pos]) {
                // If the value from array1 is larger than what were comparing against in array2, step to the next value in array2
                array2Pos++;
            } else if (array1[array1Pos] == array2[array2Pos]) {
                // If the values are equal, increase the amount of identical values and step both arrays to the next values
                sameValues++;
                array1Pos++;
                array2Pos++;
            } else if (array1[array1Pos] < array2[array2Pos]) {
                // If the value from array2 is larger than what were comparing against, step to the next value in array1.
                array1Pos++;
            }
        }
        return sameValues;
    }

    /**
     * Sorts the contents of an integer array to ascending order.
     * 
     * Uses selection sort for the sorting. The parameter array contents are not changed.
     * @param array Array of values to be sorted
     * @return A new array containing the values sorted to ascending order.
     */
    public static int [] sort(int [] array) {
        int[] sorted = new int[array.length];
        for (int i = 0; i < sorted.length; ++i) {
            sorted[i] = array[i];
        }

        // Classic selection sort by yours truly
        int smallestIndex = 0;
            for (int i = 0; i < sorted.length; i++) {
                // Find smallest in unsorted section
                smallestIndex = i; // Remember to reset smallestIndex
                for (int j = i + 1; j < sorted.length; j++) {
                    if (sorted[j] < sorted[smallestIndex]) {
                        smallestIndex = j;
                    }
                }
                // Move smallest number to start of unsorted area (if not already there)
                if (smallestIndex != i) {
                    int temp = sorted[i];
                    sorted[i] = sorted[smallestIndex];
                    sorted[smallestIndex] = temp;
                }
            }
        return sorted;
    }

    /**
     * Converts an integer array to an array of strings.
     * 
     * @param array Array of values to be converted to strings.
     * @return Array containing string representations.
     */
    public static String[] toStringArray(int[] array) {
        String[] stringArray = new String[array.length];
        for (int i = 0; i < stringArray.length; ++i) {
            stringArray[i] = Integer.toString(array[i]);
        }
        return stringArray;
    }

    /**
     * Prepends a prefix to an array of strings so that each string is the same length as the longest string.
     * The added length is filled with the provided character. For example prefixing the array {"longstring", "short"}
     * with the character 'x' would result in {"longstring", "xxxxxshort"}. You can also force the prefix to fill
     * up to desired minimum length, even if the longest string would be shorter than this. For example using a minimum
     * prefix length of 4 would transform {"ab", "cde"} into {"xxab", "xcde"}
     * 
     * @param array Array containing strings to prefix
     * @param prefix The character to use for prefixing
     * @param minimumPrefix The minimum length to forcefully prefix to
     * @return Array where all string are prefixed to the same length
     */    
    public static String[] prefixStringArray(String[] array, char prefix, int minimumPrefix) {
        String[] prefixedArray = new String[array.length];
        
        // Get the length we need to fill the prefix
        int length = minimumPrefix;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].length() > length) {
                length = array[i].length();
            }
        }

        // Fill the new array
        String filler = "" + prefix;
        for (int i = 0; i < prefixedArray.length; ++i) {
            prefixedArray[i] = filler.repeat(length - array[i].length()) + array[i];
        }

        return prefixedArray;
    }
}
