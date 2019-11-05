package com.company;

import java.util.*;

class Edge {
    public int v1, v2;

    public int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}

public class CableNetwork {

    public static void algorithmByPrim(int numberV, ArrayList<Edge> E, ArrayList<Edge> MST) {
        //неиспользованные ребра
        var notUsedE = new ArrayList<>( E );
        //использованные вершины
        ArrayList<Integer> usedV = new ArrayList<>();
        //неиспользованные вершины
        ArrayList<Integer> notUsedV = new ArrayList<>();
        for (int i = 1; i <= numberV; i++)
            notUsedV.add( i );
        //выбираем случайную начальную вершину

        Random rand = new Random();
        usedV.add( rand.nextInt(numberV) );
        notUsedV.remove( usedV.get( 0 ) );
        while (notUsedV.size() > 0) {
            int minE = -1; //номер наименьшего ребра
            //поиск наименьшего ребра
            for (int i = 0; i < notUsedE.size(); i++) {
                if ((usedV.indexOf( notUsedE.get( i ).v1 ) != -1)
                        && (notUsedV.indexOf( notUsedE.get( i ).v2 ) != -1)
                        || (usedV.indexOf( notUsedE.get( i ).v2 ) != -1)
                        && (notUsedV.indexOf( notUsedE.get( i ).v1 ) != -1)) {
                    if (minE != -1) {
                        if (notUsedE.get( i ).weight < notUsedE.get( minE ).weight)
                            minE = i;
                    } else
                        minE = i;
                }
            }
            //заносим новую вершину в список использованных и удаляем ее из списка неиспользованных
            if (usedV.indexOf( notUsedE.get( minE ).v1 ) != -1) {
                usedV.add( notUsedE.get( minE ).v2 );
                notUsedV.remove( notUsedV.indexOf( notUsedE.get( minE ).v2 ) );
            } else {
                usedV.add( notUsedE.get( minE ).v1 );
                notUsedV.remove( notUsedV.indexOf( notUsedE.get( minE ).v1 ) );
            }
            //заносим новое ребро в дерево и удаляем его из списка неиспользованных
            MST.add( notUsedE.get( minE ) );
            notUsedE.remove( minE );
        }
    }

    private static Set<Integer> integerSet = new HashSet<>();

    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        String expression = scanner.nextLine();

        String[] array = expression.split( " " );

        int[] numberComp = new int[3];
        for (int i = 0; i < 3; i++) {
            numberComp[i] = Integer.valueOf( array[i] );
        }

        ArrayList<Edge> E = new ArrayList<>();
        for (int i = 0; i < numberComp[2]; i++) {
            String[] m = scanner.nextLine().split( " " );
            int[] e = new int[3];
            e[0] = Integer.valueOf( m[0] );
            e[1] = Integer.valueOf( m[1] );
            e[2] = Integer.valueOf( m[2] );
            Edge edge = new Edge( e[0], e[1], e[2] );
            E.add( edge );
            integerSet.add( e[0] );
            integerSet.add( e[1] );
        }

        if (integerSet.size() != numberComp[0]) {
            System.out.println( "-1" );
            System.exit( 1 );
        }

        if (integerSet.size() == 2 && numberComp[2] == 1) {
            System.out.println( E.get( 0 ).weight );
            System.exit( 0 );
        }

        ArrayList<Edge> MST = new ArrayList<>();
        algorithmByPrim( numberComp[0], E, MST );

        long answer = 1;
        for (int i = 0; i < MST.size(); i++) {
            answer *= MST.get( i ).weight % (Math.pow( 2, 31 ) - 1);
        }

        System.out.println( answer );
    }
}
