package com.dsaninja.algos.search;

/**
 * The class is used to demonstrate the linear search algorithm
 * for an int array.
 *
 * <h1>Complexity</h1>
 * <ol>
 *     <li>Best Case: O(1) - the first element matches the key</li>
 *     <li>Worst Case: O(n) - all the elements have to be traversed</li>
 * </ol>
 *
 * @author gaurs
 */
public class LinearSearch{
    /**
     * The method is used to linearly search an input array for a given value.
     *
     * @param input the input array to be searched
     * @param key   the value to be searched
     * @return 0 based index if value is found; - 1 otherwise
     */
    public int linearSearch(int[] input, int key){
        for(int i = 0; i < input.length; i++){
            if(key == input[i]){
                return i;
            }
        }
        return -1;
    }
}
