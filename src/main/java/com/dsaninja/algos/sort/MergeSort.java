package com.dsaninja.algos.sort;

/**
 * MergeSort implementation in java. The idea is to recursively partition
 * the input array until single element arrays are left and then merge those
 * in increasing order.
 *
 * This requires auxiliary space and is a stable sorting algorithm.
 *
 * <h1>Complexity</h1>
 * <ol>
 *     <li>Best Case: O(nlog(n))</li>
 *     <li>Worst Case: O(nlog(n))</li>
 * </ol>
 *
 * @author gaurs
 */
public class MergeSort{

    public void sort(int[] input){
        int length = input.length;
        divideAndConquer(input, 0, length - 1);
    }

    /**
     * Recursively partition the array and then merge those in sorted order
     *
     * @param input input array to be sorted
     * @param start start of current iteration
     * @param end   last element to be considered in current iteration
     */
    private void divideAndConquer(int[] input, int start, int end){
        // end is length -1 but start <= end is of no use
        // as you can't partition a single element
        if(start < end){
            int mid = (start + end) / 2;
            // array[start...mid]
            divideAndConquer(input, start, mid);

            // array[mid+1.. end]
            divideAndConquer(input, mid + 1, end);

            // merge the array in sorted order
            // array[start... mid ... end]
            merge(input, start, mid, end);
        }
    }

    private void merge(int[] input, int start, int mid, int end){
        // create new temp array of size end-start+1
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        // both mid and end are compared inclusively
        // as mid is included in first half
        // and end was: length -1
        while(i <= mid && j <= end){
            if(input[i] < input[j]){
                // if first half
                temp[k++] = input[i++];
            } else{
                // if second half
                temp[k++] = input[j++];
            }
        }

        // copy remaining elements including mid
        while(i <= mid){
            temp[k++] = input[i++];
        }

        // copy remaining elements including end
        while(j <= end){
            temp[k++] = input[j++];
        }

        // copy from temp to input
        // use start index in original array
        System.arraycopy(temp, 0, input, start, temp.length);
    }
}
