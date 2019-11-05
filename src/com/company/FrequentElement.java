package com.company;

import java.util.*;

public class FrequentElement {

    public static int evaluate(String statement, int n) {
        ArrayList<String> arr = new ArrayList<>( Arrays.asList( statement.split( " " ) ) );

        int[] result = new int[n];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.valueOf( arr.get( i ) );
        }

        Map<Integer, Integer> frequency = new HashMap<>();
        int count = 1, value;
        for (int i = 0; i < result.length; i++) {
            if (frequency.containsKey( result[i] )) {
                value = frequency.get( result[i] );
                frequency.replace( result[i], ++value );
            } else {
                frequency.put( result[i], count );
            }
        }

        int max = 0;
        int maxValue = 0;
        for (Integer i : frequency.keySet()) {
            if (frequency.get( i ) > maxValue) {
                maxValue = frequency.get( i );
                max = i;
            }
            if (frequency.get( i ).equals( frequency.get( max ) )) {
                if (i > max) {
                    max = i;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        int n = Integer.valueOf( scanner.nextLine() );
        String a = scanner.nextLine();

        //run
        System.out.println( evaluate( a, n ) );
    }
}
