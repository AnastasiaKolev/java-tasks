package com.company;

public class BitOperations {
    /**
     * Дан большой файл целых чисел.
     * Все числа повторяются два раза, кроме одного числа, которое не повторяется.
     * Найдите это число одним проходом по файлу.
     */
    private static int findUniqueNumber(int[] arr) {
        int number = arr[0];
        for (int i = 1; i < arr.length; i++)
            number = number ^ arr[i];
        return number;
    }

    /**
     * Напишите функцию, определяющую количество бит,
     * которое надо изменить, чтобы из числа A получить число B.
     * Пример: Даны числа: 29 и 15, найти количество бит,
     * необходимое изменить, чтобы из 29 получилось число 15:
     * 29 (10) = 11101 (2)
     * 15 (10) = 01111 (2)
     */
    private static int countDiffBits(int a, int b) {
        int diffBits = a ^ b;
        int bitsCount = 0;

        while (diffBits != 0) {
            if ((diffBits & 1) == 1) bitsCount++;
            diffBits = diffBits >>> 1;
        }
        return bitsCount;
    }

    /**
     * Напишите алгоритм, меняющий четные и нечетные биты местами в целом
     * беззнаковом числе (тип uint32) используя минимальное количество операций.
     * Пример
     * input:  0011 1010 0101 1001 0110 1101 0110 1111
     * output: 0011 0101 1010 0110 1001 1110 1001 1111
     */
    private static int pairwiseSwap(int num) {
        int oddMask  = 0xAAAAAAAA; // 1010 1010 1010 1010 1010 1010 1010 1010
        int evenMask = 0x55555555; // 0101 0101 0101 0101 0101 0101 0101 0101

        oddMask = (num & oddMask); // занулили все четные биты
        oddMask = oddMask >>> 1; // сдвинули нечетные биты на место четных

        evenMask = (num & evenMask); // занулили все нечетные биты
        evenMask = evenMask << 1;  // сдвинули четные биты на место нечетных

        return oddMask | evenMask;   // соединяем сдвинутые биты
    }

    public static void main(String[] args) {
        // given findNumber
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 99, 10, 10, 11, 11, 12, 12};

        // run findNumber
        System.out.println("Unique number in array is: " + findUniqueNumber( arr ) );

        // given countDiffBits
        int a = 34, b = 15;

        // run countDiffBits
        System.out.println( "a = " + a + " binary " + Integer.toBinaryString( a )
                + "\nb = " + b + " binary " + Integer.toBinaryString( b )
                + "\nDifferent bits count: " + countDiffBits( a, b ) );

        // given pairwiseSwap
        int num = 0xABCD9731;

        // run pairwiseSwap
        System.out.println("Pairwise Swap is: " + pairwiseSwap( num ) );

    }
}
