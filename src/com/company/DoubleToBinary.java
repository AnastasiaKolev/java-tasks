package com.company;

import java.util.Scanner;

public class DoubleToBinary {
    private static String doubleToBinary(double num) {
        if (num >= 1 || num <= 0) {
            return "Error";
        }

        StringBuilder binary = new StringBuilder();
        binary.append( "." );

        while (num > 0) {
            if (binary.length() > 23) {
                return "Error";
            }
            double fractional = num * 2;
            if (fractional >= 1) {
                binary.append( "1" );
                num = fractional - 1;
            } else {
                binary.append( "0" );
                num = fractional;
            }
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        double n = scanner.nextDouble();

        //run
        System.out.println( doubleToBinary( n ) );
    }
}
