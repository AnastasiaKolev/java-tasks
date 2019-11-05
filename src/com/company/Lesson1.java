package com.company;

import java.util.Scanner;

public class Lesson1 {

    public static void main(String[] args) {
        //Ex. 1
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Enter array size: " );
        int arrSize = scanner.nextInt();

        int[] arr = new int[arrSize];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*100);
            System.out.print( arr[i] + "\t");
        }

        double max = arr[0];
        double min = arr[0];
        double average = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
            average += arr[i]/arr.length;
        }
        System.out.println( "\nMax: " + max +
                "\nMin: " + min +
                "\nAvg: " + average);

        //Ex.2
        Scanner scanner2 = new Scanner( System.in );
        System.out.println( "Enter N number for algebraic sum: " );
        int num = scanner2.nextInt();
        System.out.println( "Enter k Exponentiation: " );
        int k = scanner2.nextInt();

        int sum = 0;
        for (int i = 1; i < num + 1; i++) {
            sum += (int)Math.pow( i, k );
        }
        System.out.println( "Algebriac sum: " + sum);

        //Ex.3


        /*
            Задача найти самую длинную непрерывную строго возрастающую последовательность
         */
        System.out.println( "Найти самую длинную непрерывную строго возрастающую последовательность\n"
                + "array = {1, 2, 3, 3, 4, 2, 3, 4, 4, 5, 6, 7, 8, 9, 1}" );
        int[] array = {1, 2, 3, 3, 4, 2, 3, 4, 4, 5, 6, 7, 8, 9, 1};
        int counter = 1;
        int maxSubsequence = 0;
        int startIndex = 0;
        int maxIndex = array.length;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1]) {
                if (counter == 1) {
                    startIndex = i;
                }
                counter++;
            } else {
                counter = 1;
            }
            if (maxSubsequence < counter) {
                maxSubsequence = counter;
                maxIndex = startIndex;
            }
        }


        if (maxIndex == array.length) {
            System.out.println( "No such subsequence" );
        } else {
            for (int i = maxIndex; i < maxIndex + maxSubsequence; i++) {
                if (array[i] == array[i + 1]) {
                    continue;
                } else {
                    System.out.print( array[i] + " " );
                }
            }
        }
    }

}
