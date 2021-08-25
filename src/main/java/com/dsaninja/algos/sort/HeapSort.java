package com.dsaninja.algos.sort;

/**
 * The heap sort algorithm implementation in java. The heap sort is performed
 * in three steps:
 * <ol>
 *     <li>Build Max Head</li>
 *     <li>Swap root element with last element</li>
 *     <li>Heap-ify again</li>
 *     <li>goto step 2</li>
 * </ol>
 * <p>
 * This is an in-place sorting algorithm. It is NOT a stable sorting algorithm.
 *
 * <h1>Complexity</h1>
 * <ol>
 *     <li>Best Case: O(nlog(n))</li>
 *     <li>Worst Case: O(nlog(n))</li>
 * </ol>
 *
 * @author gaurs
 */
public class HeapSort{

    /**
     * Sort the input array in increasing order of elements using heap sort.
     *
     * @param input the array to be sorted
     */
    public void sort(int[] input){
        // build max heap
        buildMaxHeap(input);

        // the max heap will have the largest element at 0th location
        // so lets swap root element with tail
        // to re-position it at end
        int size = input.length - 1;
        while(size >= 0){
            int temp = input[0];
            input[0] = input[size];
            input[size] = temp;
            size--;

            // the heapify method is called on 1 less element
            // in every iteration
            heapify(input, 0, size);
        }
    }

    /**
     * Why do we start from the mid of the array and not from end or start?
     * Check <a href="https://stackoverflow.com/a/40822526">this</a> link for details.
     * <p>
     * Another explanation at <a href="https://cs.stackexchange.com/a/89645">this</a>
     * link suggests that as the left and right indices are calculated using <code>2*i+1</code>
     * and <code>2*i+2</code>, this gives us the higher chances of having the calculated
     * values in range as otherwise we do not shift the value down in tree and is a no-op.
     *
     * @param input array to be sorted.
     */
    private void buildMaxHeap(int[] input){
        int length = input.length - 1;
        for(int i = length / 2; i >= 0; i--){
            heapify(input, i, length);
        }
    }

    private void heapify(int[] input, int start, int length){
        int left = start * 2 + 1;
        int right = start * 2 + 2;

        int maxBetweenLeftAndRight = start;
        // length is already size -1 so <= check
        if(left <= length && input[left] > input[start]){
            maxBetweenLeftAndRight = left;
        }

        // instead of comparing right with root and left
        // simply compare right with maxBetweenLeftAndRight which
        // will be pointing to the max of start and left
        if(right <= length && input[right] > input[maxBetweenLeftAndRight]){
            maxBetweenLeftAndRight = right;
        }

        // i.e. either left or right is greater than start
        if(maxBetweenLeftAndRight != start){
            int temp = input[start];
            input[start] = input[maxBetweenLeftAndRight];
            input[maxBetweenLeftAndRight] = temp;

            // as a swap might have broken the max heap property
            // re-heapify
            heapify(input, maxBetweenLeftAndRight, length);
        }
    }
}
