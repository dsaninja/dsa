 package com.dsaninja.algos.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeSort{

    public static void main(String[] args){
        TreeSort ts = new TreeSort();
        System.out.println(Arrays.toString(ts.sort(new int[]{8, 6, 10, 5, 9, 7, 11})));
    }

    private static final class TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int value){
            this.value = value;
        }
    }

    public int[] sort(int[] input){
        TreeNode root = null;
        for(int element : input){
            root = populateTree(root, element);
        }

        var resultList = new ArrayList<Integer>();
        inorder(root, resultList);
        return resultList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void inorder(TreeNode root, List<Integer> result){
        if(null == root){
            return;
        }

        inorder(root.left, result);
        result.add(root.value);
        inorder(root.right, result);
    }

    private TreeNode populateTree(TreeNode root, int element){
        if(null == root){
            root = new TreeNode(element);
        } else{
            if(element < root.value){
                root.left = populateTree(root.left, element);
            } else{
                root.right = populateTree(root.right, element);
            }
        }
        return root;
    }
}
