package com.company;

import java.util.Random;

public class FindPoisonedBottle {
    /**
     * Есть 1000 бутылок с водой, 1 отравлена
     * Дано 10 индикаторов для определения яда
     * Капля яда окрашивает индикатор
     * Налив яд и воду одновременно на один индикатор, он окрасится как в яд
     * Можно капать на индикатор любым количеством капель
     * Можно заново использовать индикатор после теста, если яд не был обнаружен
     * Тест можно проводить 1 раз в день, результат будет известен через 7 дней
     */
    private static String findPoison(int bottles, int indicators) {
        // check if bottles max number fits in 10 bits (amount of indicators)
        int capacity = (int) Math.pow( 2, indicators );
        if (bottles > capacity) {
            int weeks = bottles/capacity;
            return "The tests will take " + (++weeks) + " weeks!";
        }

        // generate random poisoned bottle
        Random rand = new Random();
        int poisoned = rand.nextInt( bottles ) + 1;


        // create array of drops amount on each indicator
        int[] drops = new int[indicators];

        // drop on each indicator number corresponding to binary '1' of the bottle's number
        for (int j = 1; j <= bottles; j++) {
            //convert int to binary string of fixed size
            String temp = toBinary( j, indicators );

            assert temp != null;
            char[] bottleNum = temp.toCharArray();

            for (int i = 0; i < indicators; i++) {
                if (bottleNum[i] == '1') {
                    drops[i]++;
                }
            }
        }

        return "The results will arrive in 7 days\n" + getResult( drops, poisoned );
    }

    private static String getResult(int[] drops, int poisoned) {
        String result = toBinary( poisoned, drops.length );
        char[] resArray = result.toCharArray();

        StringBuilder res = new StringBuilder( "The indicators under № " );

        for (int i = 0; i < drops.length; i++) {
            if (resArray[i] != '0') {
                int indicator = i + 1;
                res.append( indicator + " " );
            }
        }

        res.append( " reveal the poisoned bottle with binary number: " + result );

        return res.toString();
    }

    private static String toBinary(int x, int len) {
        if (len > 0) {
            return String.format( "%" + len + "s",
                    Integer.toBinaryString( x ) ).replaceAll( " ", "0" );
        }

        return null;
    }


    public static void main(String[] args) {
        //given
        int bottles = 1000;
        int indicators = 10;

        //run
        System.out.println( findPoison( bottles, indicators ) );
    }
}
