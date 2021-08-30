package com.dsaninja.algos.sort;

import java.util.Arrays;

/**
 * Java implementation of RadixSort algorithm. This algorithm works on the individual
 * decimal positions for every number and use counting sort to sort the numbers based
 * on current decimal location (ones, tens, a hundred etc.)
 * <p>
 * This also caters to a more broad range of elements where the maxValue is greater
 * than the array size. This would have wasted a lot of space wit count sort.
 *
 * <ol>
 *  <li>Best Case: O(n+r)</li>
 *  <li>Worst Case: O(n+r)</li>
 *  <li>Space: O(n+r)</li>
 * </ol>
 *
 * @author gaurs
 */
public class RadixSort{
    public void sort(int[] input){
        // find maxElement, this will be used to identify how many
        // times we need to trigger the count sort - based on the
        // number of digits it has
        int maxElement = findMax(input);
        if(maxElement != -1){
            // start from the unit place and move towards tens, hundred and so-on
            // for all the digits in the maxElement
            for(int base = 1; maxElement / base > 0; base = base * 10){
                // sort based on the current base (1, 10, 100 ..)
                sort(input, base);
            }
        }
    }

    private void sort(int[] input, int base){
        int length = input.length;
        // as digit on every place (unit place, 10s place, 100s place etc will be between 0..9
        // and we are sorting based on a single base (1 or 10 or 100s etc)
        // the frequency array will be of size 10 only (as every base can be 0..9)
        int freqArraySize = 10;

        int[] frequencies = new int[freqArraySize];
        int[] result = new int[length];

        // update frequency element based on the current
        // base 1 or 10 or 100th etc
        for(int i = 0; i < length; i++){
            ++frequencies[(input[i] / base % 10)];
        }

        // cumulative frequencies
        for(int i = 1; i < freqArraySize; i++){
            frequencies[i] = frequencies[i] + frequencies[i - 1];
        }

        // shift right
        for(int i = freqArraySize - 1; i > 0; i--){
            frequencies[i] = frequencies[i - 1];
        }
        // and add 0 at 0th index
        frequencies[0] = 0;

        // populate result
        for(int i = 0; i < length; i++){
            // based on the current base
            // update result[index] where index is calculated
            // based on the current base value in input
            // i.e. for 163 and base =1 check value at 3rd index
            // in frequency table and use that as an index for result
            result[frequencies[(input[i] / base % 10)]] = input[i];
            ++frequencies[(input[i] / base % 10)];
        }

        // copy result to original array
        System.arraycopy(result, 0, input, 0, length);
    }

    private int findMax(int[] input){
        return Arrays.stream(input).max().orElse(-1);
    }
}
