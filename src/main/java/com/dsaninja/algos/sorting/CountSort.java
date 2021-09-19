package com.dsaninja.algos.sorting;

import java.util.Arrays;

/**
 * Count sort implementation in java. The algorithm is a non-comparison
 * sorting algorithm and runs in linear time.
 * <p>
 * There are a few restrictions like:
 * <ol>
 *     <li>Counting sort only works when the range of potential items in the input is known ahead of time.</li>
 *     <li>If the range of potential values is big, then counting sort requires a lot of space</li>
 * </ol>
 *
 * <h1>Complexity</h1>
 * For an array of size <code>n</code> having maximum value as <code>r</code>:
 * <ol>
 *     <li>Best Case: O(n+r)</li>
 *     <li>Worst Case: O(n+r)</li>
 *     <li>Space: O(n+r)</li>
 * </ol>
 *
 * @author gaurs
 */
public class CountSort{
    public void sort(int[] input){
        if(input.length > 0){
            // each value in frequency array denotes the number of times
            // that value is present in original array.
            int[] frequencies = populateFrequencies(input);

            // For any given index i, the frequency at ith location
            // tells us the number of slots that element will take in
            // the output array. In other words, when traversing the frequency
            // array from left to right, we can estimate the location of every element
            // using the frequency of its previous elements:
            // say input: [1, 5, 3, 1, 2, 4] and freq: [O(0), 1(2), 2(1), 3(1), 4(1), 5(1)]
            // its clear that 1 will take two slots (as its freq is 2) in the output
            // so, 2 can come only at 3rd location or index 2 to accommodate all
            // occurrences of 1.
            // To propagate this across all the elements and not just first two elements
            // lets add the freq array cumulatively
            for(int i = 1; i < frequencies.length; i++){
                frequencies[i] = frequencies[i] + frequencies[i - 1];
            }

            // This makes the freq array as [0(0), 1(2), 3(3), 4(4), 5(5), 6(6)]
            // On a second look, we can conclude that every value
            // now represents the index of next element in the final sorted
            // result i.e. 0 at 0th index represents location of 1 in final output
            // 2 at 1st index represents index of 3 in final output and so-on
            // to match the index with its value, now lets shift it to the right
            System.arraycopy(frequencies, 0, frequencies, 1, frequencies.length - 1);

            // and add 0 at 0th index
            frequencies[0] = 0;

            int[] result = new int[input.length];

            // use every value in the input as an index for frequency array
            // and put the corresponding value in result
            for(int i = 0; i < input.length; i++){
                result[frequencies[input[i]]] = input[i];
                // increment the frequency count by 1 to
                // accommodate next occurrence of same value input[i]
                frequencies[input[i]]++;
            }

            // copy result to input
            System.arraycopy(result, 0, input, 0, input.length);
        }
    }

    private int[] populateFrequencies(int[] input){
        int maxValue = Arrays.stream(input).max().getAsInt();
        int[] freq = new int[maxValue + 1];
        // freq[input[i] = freq[input[i] + 1
        for(int index : input){
            freq[index] = freq[index] + 1;
        }
        return freq;
    }
}
