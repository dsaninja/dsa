package com.dsaninja.algos.sort;

/**
 * Another sorting algorithm (probably the easiest one) which involves
 * comparing adjacent elements and swapping them if those are ur of order.
 *
 * After every iteration, the largest element (out of the un-sorted elements)
 * thus moves to the rightmost position. If during any intermediate iteration,
 * we find that there was no swapping in the current iteration, we can terminate
 * the loops as this indicates that the sorting is complete.
 *
 * <h1>Complexity</h1>
 *  * <ol>
 *  *    <li>Best Case: O(n) - already sorted input; via flag indicating no swap</li>
 *  *    <li>Worst Case: O(n*n)</li>
 *  * </ol>
 *
 * @author gaurs
 */
public class BubbleSort{

    public void sort(int[] input){
        int length = input.length;

        boolean unsorted = false;
        for(int i = 0; i < length; i++){
            // In every iteration as adjacent elements are compared we only need
            // to go till second last element via index "j" as for
            // "j" pointing to second last element (j < length -1), j+1 will point
            // to last element.

            // Also, as in every iteration, the rightmost element will be correctly placed,
            // we can skip the same in next iteration to improve the performance via:
            // j < length -1 -i

            // Moreover, as the rightmost element is sorted in each iteration, this loop
            // starts from 0 to always begin the comparison from 1st two elements
            for(int j = 0; j < length - 1 - i; j++){
                // if out of place, then swap
                if(input[j] > input[j + 1]){
                    unsorted = true;

                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j+1] = temp;
                }
            }

            // if there was no swapping in current iteration, this means the array is now
            // sorted, and we can break the loop.
            if(!unsorted){
                break;
            }
        }
    }
}
