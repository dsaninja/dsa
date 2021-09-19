package com.dsaninja.algos.sorting;

/**
 * The SelectionSort algorithm implemented in java. The idea is to find a suitable
 * candidate (minimum) for a given location in every iteration.
 * <p>
 * To do this, every element is compared with elements to its right and in case
 * the two are our of place, the smaller element is posted on the current index.
 * After every iteration, the current index position will have the correct element.
 *
 * This is an in-place, but NOT a stable sorting algorithm.
 *
 * <h1>Complexity</h1>
 * <ol>
 *    <li>Best Case: O(n*n)</li>
 *    <li>Worst Case: O(n*n)</li>
 * </ol>
 *
 * @author gaurs
 */
public class SelectionSort{

    public void sort(int[] input){
        int length = input.length;

        // as every element is compared with all the elements to its right
        // we only need to go till second last element via index "i" as for
        // "i" pointing to second last element (i < length -1), j will point
        // to last element (j < length)
        for(int i = 0; i < length - 1; i++){
            for(int j = i + 1; j < length; j++){
                // if out of place, then swap
                if(input[i] > input[j]){
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
    }
}
