package com.dsaninja.algos.sort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BucketSort implementation based on the standard java sort algorithm
 * for <strong>rational</strong> numbers.
 * <p>
 * The algorithm works as follows:
 * <ol>
 *     <li>create 10 buckets; a bucket will contain all elements that map to it via: <code>Math.floor(element * length)</code></li>
 *     <li>sort individual buckets</li>
 *     <li>copy to original array</li>
 * </ol>
 *
 * @author gaurs
 */
public class BucketSortForRationalNumbers{

    public void sort(double[] input){
        int length = input.length;
        if(length > 0){
            // create and initialize 10 buckets
            List<Double>[] buckets = createBuckets();

            // put every element from input to one of the buckets.
            // a decimal number when multiplied with length of array
            // can be used to calculate an integer index
            for(double element : input){
                int index = (int) Math.floor(element * length);
                buckets[index].add(element);
            }

            // sort individual buckets
            for(List<Double> bucket : buckets){
                Collections.sort(bucket);
            }

            // copy the sorted buckets to original array
            AtomicInteger resultIndex = new AtomicInteger(0);
            for(List<Double> bucket : buckets){
                bucket.forEach(element -> input[resultIndex.getAndIncrement()] = element);
            }
        }
    }

    /**
     * Create 10 buckets for 0..9 digits. Each bucket will contain a list
     * of element that has same digits after mapping
     *
     * @return bucket array
     */
    private List<Double>[] createBuckets(){
        List<Double>[] buckets = new LinkedList[10];
        for(int i = 0; i < 10; i++){
            buckets[i] = new LinkedList<>();
        }

        return buckets;
    }
}
