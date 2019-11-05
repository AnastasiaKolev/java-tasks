
package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class NeighboursParity {

    public static String evaluate(String statement) {
        ArrayList<String> expression = new ArrayList<>( Arrays.asList(statement.split( " " )) );

        ArrayList<Integer> nums = new ArrayList<>( );
        for (String item : expression) {
            nums.add( Integer.parseInt( item ) );
        }

        int odd = 0;
        int even = 0;
        for (Integer item : nums) {
            if (item % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if (Math.abs( odd - even ) >= 2) {
            System.out.println( "-1" );
            return "-1";
        }

        ArrayList<Integer> result = new ArrayList<>( );
        Stack<Integer> stack = new Stack<>( );
        int prev = -1;
        int curr;
        int k = 0;
        if (odd - even == 1) {
            for (int i = 0; i < nums.size(); i++) {
                curr = nums.get( i );
                if (curr % 2 == 1 && prev == -1) {
                    result.add(curr);
                    prev = result.get( 0 );
                    if (stack.size() > 0) {
                        prev = stack.pop();
                        result.add( prev );
                        k++;
                    }
                } else if (prev % 2 != curr % 2 && prev != -1) {
                    result.add( curr );
                    prev = curr;
                    if (stack.size() > 0) {
                        prev = stack.pop();
                        result.add( prev );
                        k++;
                    }
                } else {
                    stack.push( nums.get( i ) );
                }
            }

            if ( stack.size() > 0) {
                result.add( stack.pop() );
            }
        } else if (even - odd == 1) {
            for (int i = 0; i < nums.size(); i++) {
                curr = nums.get( i );
                if (curr % 2 == 0 && prev == -1) {
                    result.add(curr);
                    prev = result.get( 0 );
                    if (stack.size() > 0) {
                        prev = stack.pop();
                        result.add( prev );
                        k++;
                    }
                } else if (prev % 2 != curr % 2 && prev != -1) {
                    result.add( curr );
                    prev = curr;
                    if (stack.size() > 0) {
                        prev = stack.pop();
                        result.add( prev );
                        k++;
                    }
                } else {
                    stack.push( curr );
                }
            }

            if ( stack.size() > 0) {
                result.add( stack.pop() );
            }
        } else {
            result.add( nums.get( 0 ) );
            prev = result.get( 0 );
            for (int i = 1; i < nums.size(); i++) {
                curr = nums.get( i );
                if (prev % 2 != curr % 2) {
                    result.add( curr );
                    prev = curr;
                    if (stack.size() > 0
                            && stack.peek() % 2 != prev % 2 ) {
                        prev = stack.pop();
                        result.add( prev );
                        k++;
                    }
                } else {
                    stack.push( curr );
                }
            }

            if ( stack.size() > 0) {
                result.add( stack.pop() );
            }
        }

        System.out.println( k );

        for (int i = 0; i < result.size(); i++) {
            System.out.print( result.get( i ) );
            if (i != result.size() - 1 ) {
                System.out.print( " " );
            }
        }

        return "";
    }


    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        String n = scanner.nextLine();
        String a = scanner.nextLine();

        //run
        evaluate(a);
    }
}
