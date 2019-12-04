package com.company;

import java.util.Scanner;

public class ParenthesesPlacement {
    /**
     * Напишите функцию, генерирующую все верные, уникальные комбинации из N пар круглых скобок.
     */
    private static void generateParens(String str, int left, int right) {
        if (left < 0 || right < 0 || right < left ) return;
        if (left == 0 && right == 0) {
            System.out.println(str);
            return;
        }

        generateParens(str + "(", left - 1, right);
        generateParens(str + ")", left, right - 1);
    }

    private static void generateParens(String str, int parensCount) {
        generateParens(str, parensCount, parensCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int in = scanner.nextInt();

        generateParens("", in);
    }
}
