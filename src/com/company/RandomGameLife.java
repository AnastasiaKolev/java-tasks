package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RandomGameLife {

    private static void apply(Integer[][] arr, Integer[][] arrNew, Integer[][] state, int in, int out) {
        if (arr[out][in] != arrNew[out][in]) { // main check of point state after iteration
            state[out][in]++;
        }
    }

    private static void evaluate(String[][] statement, int n, int m, int k) {

        Integer[][] arr = new Integer[n][m]; // arr of ints
        for (int i = 0; i < statement.length; i++) {
            for (int j = 0; j < statement[i].length; j++) {
                arr[i][j] = Integer.valueOf( statement[i][j] );
                if ( arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 3) {

                } else {
                    System.exit(1);
                }
            }
        }

        Integer[][] state = new Integer[n][m]; // final results arr of states
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                state[i][j] = 0;
            }
        }

        Integer[][] arrNew = new Integer[n][m]; // helper arr for creating new field for iteration
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arrNew[i][j] = arr[i][j];
            }
        }

        int con1 = 0; // condition = neighbor counter for 2
        boolean isTwo = false;
        boolean isThree = false;
        List<Integer> curr;
        for (int i = 0; i < k; i++) { // iteration's loop

            for (int out = 0; out < arr.length; out++) {
                for (int in = 0; in < arr[out].length; in++) {
                    curr = new ArrayList<>();
                    if (out == 0 || out == arr.length - 1 || arr.length == 1) {
                        if (out == 0 && arr.length != 1) {
                            curr.add( arr[out + 1][in]);
                        } else if (out == arr.length - 1 && arr.length != 1) {
                            curr.add( arr[out - 1][in]);
                        }

                        if (in == 0 && arr[out].length != 1) {
                            curr.add( arr[out][in + 1]);
                        } else if (in == arr[out].length - 1 && arr[out].length != 1 ) {
                            curr.add( arr[out][in - 1] );
                        } else if (arr[out].length != 1) {
                            curr.add( arr[out][in + 1]);
                            curr.add( arr[out][in - 1] );
                        }
                    } else if (out < arr.length - 1 || arr[out].length == 1) {
                        curr.add( arr[out + 1][in]);
                        curr.add( arr[out - 1][in]);
                        if (in != arr.length - 1 && arr[out].length != 1) {
                            curr.add( arr[out][in + 1]);
                        }
                        if (in != 0 && arr[out].length != 1) {
                            curr.add( arr[out][in - 1] );
                        }
                    } else {
                        curr.add( arr[out + 1][in]);
                        curr.add( arr[out - 1][in]);
                        curr.add( arr[out][in + 1]);
                        curr.add( arr[out][in - 1] );
                    }

                    for (int c = 0; c < curr.size(); c++) {
                        if (curr.get( c ) == 2) {
                            con1++;
                            if (con1 > 1) {
                                isTwo = true;
                            }
                        }
                        if (curr.get( c ) == 3 || con1 > 0) {
                            isThree = true;
                        }
                    }

                    if (isTwo) {
                        arrNew[out][in] = 2;
                    } else if (isThree) {
                        arrNew[out][in] = 3;
                    } else {
                        arrNew[out][in] = 1;
                    }

                    con1 = 0;
                    apply( arr, arrNew, state, in, out );

                    isTwo = false;
                    isThree = false;
                }
            }

            if (k > 1) {
                for (int q = 0; q < arrNew.length; q++) { // new arr from helper arr
                    for (int r = 0; r < arrNew[q].length; r++) {
                        arr[q][r] = arrNew[q][r];
                    }
                }
            }
        }

        if (m == 1) {
            for (int i = 0; i < state.length; i++) { // result arr of states
                for (int j = 0; j < state[i].length; j++) {
                    System.out.println( state[i][j] );
                }
            }
        } else {
            for (int i = 0; i < state.length; i++) { // result arr of states
                for (int j = 0; j < state[i].length; j++) {
                    System.out.print( state[i][j] );
                    if (j != state[i].length - 1) {
                        System.out.print( " " );
                    }
                }
                if (i != state[i].length) {
                    System.out.println();
                }
            }
        }
    }


    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        String input = scanner.nextLine();

        ArrayList<String> inputE = new ArrayList<>( Arrays.asList(input.split( " " )) );
        if (inputE.size() < 3) {
            System.exit(1);
        }
        ArrayList<Integer> nums = new ArrayList<>( );

        for (String item : inputE) {
            nums.add( Integer.parseInt( item ) );
        }

        int n = nums.get( 0 );
        int m = nums.get( 1 );
        int k = nums.get( 2 );

        if (n < 1 || m < 1 || k < 1
                || n > 100 || m > 100 || k > 100) {
            System.exit(1);
        }
        if (n == 1 && m == 1) {
            System.out.print( "0" );
            System.exit(1);
        }

        String[][] arr = new String[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLine().split( " " );
        }


        //run
        evaluate(arr, n, m, k);
    }
}