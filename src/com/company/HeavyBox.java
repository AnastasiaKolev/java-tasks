package com.company;

import java.util.Random;

public class HeavyBox {

    /**
     * Дано 20 коробок с шарами. В 19 коробках находятся шары весом 1 кг
     * В одной из коробок находятся более тяжелые шары весом в 1.1 кг
     * Даны весы, показывающие поставленный на них вес
     * Необходимо найти коробку с более тяжелыми шарами, выполнив только одно взвешивание
     * Для взвешивания из каждой коробки можно брать сколько угодно шаров
     */
    private static int findBox(int boxesCount) {
        int heavyBox = generateBox( boxesCount );
        System.out.println( "Heavy box set to be №: " +  heavyBox);

        double[] boxes = new double[boxesCount];

        //fill boxes
        for (int i = 0; i < boxesCount; i++) {
            if (heavyBox - 1 == i) {
                boxes[i] = 1.1;
            } else {
                boxes[i] = 1;
            }
        }

        double totalWeight = 0, regularWeight = 0;

        // take i-quantity of balls from each box
        for (int i = 0; i < boxesCount; i++) {
            int balls = i + 1;
            totalWeight += balls * boxes[i];
            regularWeight += balls;
        }

        // calculate
        double result = (totalWeight - regularWeight) * 10;

        // carried out miscalculations
        if (result > heavyBox - 1 && result < heavyBox) {
            result += 0.1;
        }

        return (int) result;
    }

    private static int generateBox(int boxesCount) {
        Random rand = new Random();
        return rand.nextInt( boxesCount ) + 1;
    }

    public static void main(String[] args) {
        int boxesCount = 1000;

        System.out.println( "Box is found: " + findBox( boxesCount ) );
    }
}
