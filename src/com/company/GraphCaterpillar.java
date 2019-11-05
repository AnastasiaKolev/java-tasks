package com.company;

import java.util.*;

public class GraphCaterpillar {

    public static long evaluate(String statement, int n) {
        ArrayList<String> arr = new ArrayList<>( Arrays.asList( statement.split( " " ) ) );

        ArrayList<Integer> original = new ArrayList<>();
        StringBuilder binaryMax = new StringBuilder();

        for (int i = 0; i < n; i++) {

            if (Integer.valueOf(arr.get( i )) > 100) {
                System.exit( 1 );
            }

            if (Integer.valueOf(arr.get( i )) < 0) {
                System.exit( 1 );
            }

            original.add( Integer.valueOf( arr.get( i ) ) );
            binaryMax.append( "1" );
        }

        String max = binaryMax.toString();
        int top = Integer.parseInt( max, 2 );

        long result = 0;
        for (int i = top; i >= 0; i--) {

            String temp = Integer.toBinaryString( i );
            char[] res = temp.toCharArray();

            int size = res.length;

            int stepen = 0;
            if (!temp.contains( "00" ) && size == n) {

                for (int k = 0; k < size; k++) {
                    if (res[k] == '1' && original.get( k ) != 0) {
                        stepen += original.get( k );
                    }
                }
                result += Math.pow( 2, stepen ) % 1000000007;

            } else if (!temp.contains( "00" ) && size == n - 1) {

                for (int k = 0; k < size; k++) {
                    if (res[k] == '1' && original.get( k + 1 ) != 0) {
                        stepen += original.get( k + 1 );
                    }
                }
                result += Math.pow( 2, stepen ) % 1000000007;

            } else if (size < n - 1) {
                break;
            }
        }

        result %= 1000000007;

        return result;
    }

    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        int n = Integer.valueOf( scanner.nextLine() );
        String a = scanner.nextLine();

        if (n > 100000) {
            System.exit( 1 );
        }
        if (n < 2) {
            System.exit( 1 );
        }

        //run
        System.out.println( evaluate( a, n ) );
    }
}
