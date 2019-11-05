package com.company;

public class MergeSort {

    private static void merge(int[] A, int start, int mid, int end) {
        //temporary array
        int tempArr [] = new int[end - start + 1];

        //counters
        int firstHalf = start;
        int secondHalf = mid + 1;
        int tempIndex = 0;

        while (firstHalf <= mid && secondHalf <= end) {
            if(A[firstHalf] <= A[secondHalf]) {
                tempArr[tempIndex] = A[firstHalf];
                tempIndex++;
                firstHalf++;
            } else {
                tempArr[tempIndex] = A[secondHalf];
                tempIndex++;
                secondHalf++;
            }
        }

        while(firstHalf <= mid) {
            tempArr[tempIndex] = A[firstHalf];
            tempIndex++;
            firstHalf++;
        }

        while(secondHalf <= end) {
            tempArr[tempIndex] = A[secondHalf];
            tempIndex++;
            secondHalf++;
        }

        for(int i = start; i <= end; i++) {
            A[i] = tempArr[i-start];
        }
    }

    private static void arraySort(int[] A, int p, int r) {
        if (p < r) {
            int q = Math.round((p+r)/2);
            arraySort(A, p, q);
            arraySort(A,q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void main(String[] args) {
        //given
        int[] arr = {5,2,4,6,1,3,2,6};

        //run
        arraySort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print( i );
        }
    }
}
