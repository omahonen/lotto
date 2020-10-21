package fi.tuni.tamk.tiko.ollimahonen.util;

/**
 * The class Math contains some useful mathematical methods.
 * 
 * These are not guaranteed to be scientifically accurate.
 * 
 * @author Olli Mähönen
 */


public class Math {
    /**
     * Return a random value between a given minimum and maximum values.
     * 
     * Minimum and maximum values are included in the range of possible values
     * 
     * @param min the minimum value that can be returned
     * @param max the maximum value that can be returned
     * @return randomized integer in the range [min - max]
     */
    public static int getRandom(int min, int max) {
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }
}