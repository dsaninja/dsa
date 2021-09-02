package com.dsaninja.algos.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BucketSort implementation based on the counting sort algorithm
 * for <strong>whole</strong> numbers.
 * <p>
 * The algorithm works as follows:
 * <ol>
 *     <li>find the maximum element</li>
 *     <li>for every digit starting from lsd to msd in maxElement, call countSort routine on the array</li>
 *     <li>create 10 buckets - as the digits on every place in maxElement will be between 0..9</li>
 *     <li>put the input[i] on some bucket, where bucket index is based on the current digit of input[i]</li>
 * </ol>
 *
 * @author gaurs
 */
public class BucketSortForWholeNumbers{
    public void sort(int[] input){
        int maxElement = findMaxElement(input);
        if(maxElement != -1){
            // create and initialize 10 buckets
            List<Integer>[] buckets = createBuckets();

            // for every digit in maxElement, call countSort routine
            for(int base = 1; maxElement / base > 0; base = base * 10){
                countSort(input, buckets, base);
            }
        }
    }

    private void countSort(int[] input, List<Integer>[] buckets, int base){
        // put input[i] to some index in bucket calculated based on the
        // current base value.
        // that way, all elements having same digit on current base will
        // be pushed to same bucket
        // ex: 18 and 28 will be on same bucket for base 1 as both will be mapped
        // to 8th bucket
        for(int element : input){
            int index = (element / base) % 10;
            buckets[index].add(element);
        }

        // collect the elements from the buckets in original array
        AtomicInteger resultIndex = new AtomicInteger(0);
        for(List<Integer> bucket : buckets){
            bucket.forEach(element -> input[resultIndex.getAndIncrement()] = element);
            // do not forget to clear the buckets
            bucket.clear();
        }
    }

    /**
     * Create 10 buckets for 0..9 digits. Each bucket will contain a list
     * of element that has same digits on the current place (1s, 10s, 100s etc.)
     *
     * @return bucket array
     */
    private List<Integer>[] createBuckets(){
        List<Integer>[] buckets = new LinkedList[10];
        for(int i = 0; i < 10; i++){
            buckets[i] = new LinkedList<>();
        }

        return buckets;
    }

    private int findMaxElement(int[] input){
        return Arrays.stream(input).max().orElse(-1);
    }
}
