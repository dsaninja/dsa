package com.dsaninja.algos.sorting;

/**
 * The quick sort algorithm implemented in java. The idea is based on the fact that
 * an element is said to be in sorted position if all the elements to its left are
 * smaller than it and all the elements to its right are more than it.
 *
 * This is an in-place, but NOT a stable sorting algorithm.
 *
 * <h1>Complexity</h1>
 * <ol>
 *     <li>Best Case: O(n^2)</li>
 *     <li>Worst Case: O(nlog(n))</li>
 * </ol>
 *
 * @author gaurs
 */
public class QuickSort{

    public void sort(int[] input){
        quickSort(input, 0, input.length - 1);
    }

    private void quickSort(int[] input, int start, int end){
        if(start < end){
            // find partition location
            int partitionIndex = partition(input, start, end);
            // sort first half
            quickSort(input, start, partitionIndex -1);
            // sort second half
            quickSort(input, partitionIndex + 1, end);
        }
    }

    private int partition(int[] input, int start, int end){
        int pivot = input[start];

        // next element than pivot
        int i = start + 1;
        int j = end;

        // consider all the elements in every iteration
        // within the range
        while(i < j){
            // move toward right until you find an element more than pivot
            // stop there as that element needs to be moved to the right of pivot
            // pay attention to the equality operator for input and pivot comparison
            while(input[i] <= pivot && i < end){
                i++;
            }

            // move toward left until you find an element smaller than pivot
            // stop there as that element needs to be moved to the left of pivot
            // pay attention to the equality operator for input and pivot comparison
            while(input[j] > pivot && j > start){
                j--;
            }

            // if i < j; swap these
            if(i < j){
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
        }

        if(pivot > input[j]){
            // the j will be pointing to the location meant for pivot
            // as all the elements left to j are smaller than pivot
            // and all the elements to the right of j are more than pivot
            int temp = input[j];
            input[j] = input[start];
            input[start] = temp;
        }

        return j;
    }
}
