package com.dsaninja.algos.search;

/**
 * The class is used to demonstrate the linear search algorithm
 * for an int array.
 *
 * @author gaurs
 */
public class LinearSearch {
    /**
     * The method is used to linearly search an input array for a given value.
     *
     * @param input the input array to be searched
     * @param key   the value to be searched
     * @return 0 based index if value is found; - 1 otherwise
     */
    public int linearSearch(int[] input, int key) {
        for (int i = 0; i < input.length; i++) {
            if (key == input[i]) {
                return i;
            }
        }
        return -1;
    }
}
