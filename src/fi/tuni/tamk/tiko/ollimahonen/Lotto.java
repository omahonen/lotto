package fi.tuni.tamk.tiko.ollimahonen;

import fi.tuni.tamk.tiko.ollimahonen.util.Arrays;
import fi.tuni.tamk.tiko.ollimahonen.util.Math;
import fi.tuni.tamk.tiko.ollimahonen.util.MyConsole;

/**
 * Lotto! A program that simulates playing the lottery.
 * 
 * The user can input their choice of numbers and the computer then performs the
 * drawing of the numbers. Statistics are given on how long it took get X amount of numbers correct.
 * Can also show the randomly selected numbers for each draw (this will be VERY verbose).
 * 
 * @author Olli Mähönen
 */

public class Lotto {

    static final int LOTTO_NUMBERS = 7; // The amount on numbers that are drawn in the lotto
    static final int LOTTO_HIGHEST_NUMBER = 40; // The upper limit of our number pool. Must be positive, and >=
                                                // LOTTO_NUMBERS
    static final double WEEKS_TO_YEAR = 52.14; // The amount of weeks in a year. 52 for crude conversion, 52.14 for more
                                               // accurate conversion.
    static final int LIFESPAN = 120; // Lifespan of the player in years
    static boolean showLottoNumbers = false; // Should we show each randomly drawn lotto number set

    /**
     * Main program. Gets the user's selection of lottery numbers and then
     * randomizes lottery drawings until we get all numbers right.
     * 
     * Prints out statistics on how long it took to get X amount of numbers right.
     * 
     * @param args Command line parameters. Can be used to provide the user's
     *             lottery numbers from the command line.
     */
    public static void main(String[] args) {
        int[] numbers = new int[LOTTO_NUMBERS];

        // Check if lottery numbers were given as command line arguments. Amount of
        // arguments has to match what we're expecting.
        boolean argumentConversion = false; // Indicates whether the argument conversion succeeded.
        if (args.length == numbers.length) {
            argumentConversion = true;
            for (int i = 0; i < numbers.length && argumentConversion; i++) {
                try {
                    int value = Integer.parseInt(args[i]);
                    // Check for duplicates
                    if (!Arrays.contains(value, numbers)) {
                        numbers[i] = value;
                    } else {
                        // Duplicate, set conversion to false (fail)
                        argumentConversion = false;
                    }
                } catch (NumberFormatException e) {
                    // Couldn't parse argument into an integer. Set conversion to false (fail)
                    argumentConversion = false;
                }
            }
        }

        // Ask for the user numbers if none were given as command line arguments (or
        // parsing failed)
        if (!argumentConversion) {
            String request = "Please give a unique number between [1, " + LOTTO_HIGHEST_NUMBER + "]";
            for (int i = 0; i < numbers.length; ++i) {
                System.out.println(request);
                int input = MyConsole.readInt(1, 40, "Please give a number", request);
                if (Arrays.contains(input, numbers)) {
                    System.out.println("Not a unique number!");
                    --i;
                } else {
                    numbers[i] = input;
                }
            }
        }

        // Check if the user wants to see the numbers for each round
        String[] options = { "Y", "N" };
        String input = MyConsole.readString("Do you want to see the lottery numbers for each round?", options, true);
        if (input.equalsIgnoreCase("Y")) {
            showLottoNumbers = true;
        } else if (input.equalsIgnoreCase("N")) {
            showLottoNumbers = false;
        }

        // Sort low to high
        numbers = Arrays.sort(numbers);

        boolean tryAgain = true;
        while (tryAgain) {
            int yearsPassed = 0;
            int weeksPassed = 0;
            int bestResult = 0;
            while (bestResult < 7) {
                int[] lotto = calculateLotto();
                if (showLottoNumbers) {
                    String[] userNumbers = Arrays.prefixStringArray(Arrays.toStringArray(numbers), '0', 2);
                    System.out.println("User lotto:   " + java.util.Arrays.toString(userNumbers));
                    String[] lottoNumbers = Arrays.prefixStringArray(Arrays.toStringArray(lotto), '0', 2);
                    System.out.println("Random lotto: " + java.util.Arrays.toString(lottoNumbers));
                }
                weeksPassed++;
                int matchingNumbers = Arrays.containsSameValues(numbers, lotto);
                if (matchingNumbers > bestResult) {
                    bestResult = matchingNumbers;
                    yearsPassed = (int) (weeksPassed / WEEKS_TO_YEAR);
                    System.out.println("Got " + bestResult + " right! Took " + yearsPassed + " years");
                }
            }
            System.out.println("You won.");
            if (yearsPassed > LIFESPAN) {
                System.out.println("Although it took more than a lifetime, let's try it again.");
            } else {
                tryAgain = false;
            }
        }
    }

    /**
     * Randomly selects lottery numbers from a pool of numbers. The pool size and
     * the amount of numbers to randomly draw are defined in the static variables
     * LOTTO_HIGHEST_NUMBER and LOTTO_NUMBERS respectively.
     * 
     * @return The selected lottery numbers. Array contains only unique numbers.
     *         Sorted in ascending order.
     */
    private static int[] calculateLotto() {
        int[] lotto = new int[LOTTO_NUMBERS];

        // Create new number pool
        int[] numbers = new int[LOTTO_HIGHEST_NUMBER];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = i + 1;
        }

        // Leethaxx number picking algorithm. When we pick a number, we start by
        // choosing a random position inside the pool of numbers. We copy this value to
        // final lotto number array. We then replace the value in that position with the
        // last value within the range of numbers and then we reduce the range by one
        // for the next round. Thus the value we chose has been replaced by a valid
        // option, and can no longer be chosen again in the following rounds.
        for (int i = 0; i < lotto.length; ++i) {
            int position = Math.getRandom(0, LOTTO_HIGHEST_NUMBER - 1 - i);
            lotto[i] = numbers[position];
            numbers[position] = numbers[numbers.length - 1 - i];
        }
        return Arrays.sort(lotto);
    }
}
