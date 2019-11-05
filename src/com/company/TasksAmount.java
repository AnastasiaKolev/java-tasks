package com.company;

import java.lang.*;

public class TasksAmount {
    private static final double N = 1000000;

    private static final double sec = 1;
    private static final double minute = sec * 60;
    private static final double hour = minute * 60;
    private static final double day = hour * 24;
    private static final double week = day * 7;
    private static final double year = day * 365.25f;

    public static void main(String[] args) {
        double[] time = {sec, minute, hour, day, week, year};
        String[] period = {"Sec", "Min", "Hou", "Day", "Wee", "Yea"};

        for (int i = 0; i < time.length; i++) {
            double[] func = {logN( time[i] ), sqrtN( time[i] ), time[i] * N,
                    nSq( time[i] ), twoStN( time[i] ), factN( time[i] )};
            System.out.print( period[i] + "\t\t" );
            for (int j = 0; j < func.length; j++) {
                System.out.print( func[j] + "\t\t" );
            }
            System.out.println();
        }


        for (int i = 1; ; i++) {
            if (30 * Math.pow( i, 2 ) > 1500 * i) {
                System.out.println( "30 * N^2 is better for N <= " + (i - 1) );
                break;
            }
        }

        for (int i = 1; i < N; i++) {
            if ((Math.pow( i, 3 ) / 75.0 - Math.pow( i, 2 ) / 4.0 + i + 10) < (i / 2.0 + 8)) {
                System.out.println( "N^3/75 - N^2/4 + N + 10 is better for N = " + i );

            }
        }
    }


    private static double factN(double time) {
        for (int i = 1; ; i++) {
            if (fact( i ) > N * time) {
                return i - 1;
            }
        }
    }

    private static double fact(int n) {
        double res = 1;
        for (int i = 2; i < n; i++) {
            res *= i;
        }
        return res;
    }

    private static double twoStN(double time) {
        return Math.round( Math.log( time * N ) / Math.log( 2.0 ) );
    }

    private static double nSq(double time) {
        return Math.round( Math.sqrt( time * N ) );
    }

    private static double sqrtN(double time) {
        return Math.pow( time * N, 2.0 );
    }

    private static double logN(double time) {
        return Math.pow( 2.0, time * N );
    }
}