package com.dsaninja.algos.searching;

/**
 * The class is used to demonstrate the binary search algorithm
 * for an int array. <strong>It is assumed that the input array will always
 * be a sorted one</strong>
 * <p>
 * Binary Search algorithm will work un-expectedly for un-sorted inputs.
 *
 * <h1>Complexity</h1>
 * <ol>
 *      <li>Best Case: O(1) - the first element matches the key</li>
 *      <li>Worst Case: O(log2(n))</li>
 * </ol>
 *
 * @author gaurs
 */
public class BinarySearch{

    /**
     * Binary search algorithm implementation using while loop
     *
     * @param input the input array to be searched
     * @param key   the value to be searched
     * @return 0 based index if value is found; - 1 otherwise
     */
    public int search(int[] input, int key){
        int start = 0, end = input.length - 1;

        // compare till start <= end to include the last standing element
        // as well
        while(start <= end){
            int mid = (start + end) / 2;

            if(key == input[mid]){
                return mid;
            } else if(key < input[mid]){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Binary search algorithm implementation using recursion
     *
     * @param input the input array to be searched
     * @param key   the value to be searched
     * @param start start index of current iteration
     * @param end   end index of current iteration
     * @return 0 based index if value is found; - 1 otherwise
     */
    public int search(int[] input, int key, int start, int end){
        if(start <= end){
            int mid = (start + end) / 2;
            if(key == input[mid]){
                return mid;
            } else if(key < input[mid]){
                return search(input, key, start, mid - 1);
            } else{
                return search(input, key, mid + 1, end);
            }
        }

        return -1;
    }
}
