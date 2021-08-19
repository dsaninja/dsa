package com.dsaninja.algos.sort;

/**
 * The insertion sort algorithm implemented in java. The idea is for every element
 * on index i, move all the elements to the right which are greater than the ith element
 * and place element on index i at the location where the condition breaks.
 *
 * @author gaurs
 */
public class InsertionSort{

    public void sort(int[] input){
        // assume the first element is always sorted
        // so that we can compare 2nd element with first
        for(int i = 1; i < input.length; i++){
            int index = i;
            int temp = input[i];

            // shift all the elements which are greater than i
            // to the right.
            while(index > 0 && input[index - 1] > temp){
                input[index] = input[index - 1];
                index--;
            }

            // put element on ith location on its correct position
            input[index] = temp;
        }
    }
}
