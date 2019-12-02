package com.company;

import java.util.*;

public class CableNetwork {

    /**
     * Необходимо рассчитать стоимость кабелей,
     * достаточных для организации связной сети — такой,
     * что каждый компьютер соединён с каждым прямо или опосредованно.
     * Формат ввода:
     * Первая строка содержит числа 0 < N < 10^6, 0 ≤ K < 10^6, 0 ≤ M < 10^6.
     * Следующие K строк содержат пары чисел 0 < u, v ≤ N - номера компьютеров, уже соединенных хэлпдеском.
     * Следующие M строк содержат тройки чисел 0 < u, v ≤ N и 0 < p ≤ 10^9 - номера компьютеров,
     * которые админы могут соединить при помощи кабеля стоимостью p.
     * ex.:
     * 5 0 4
     * 1 3 1
     * 2 4 8
     * 1 4 1
     * 5 3 4
     */

    static class Edge {
        int c1, c2;
        int price;

        Edge(int c1, int c2, int price) {
            this.c1 = c1;
            this.c2 = c2;
            this.price = price;
        }
    }

    static class Graph {
        int vertices;
        ArrayList<Edge> allEdges = new ArrayList<>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEgde(int v1, int v2, int weight) {
            Edge edge = new Edge( v1, v2, weight );
            allEdges.add( edge ); //add to total edges
        }

        void kruskalMST() {
            PriorityQueue<Edge> pq = new PriorityQueue<>( allEdges.size(), Comparator.comparingInt( o -> o.price ) );

            //add all the edges to priority queue, //sort the edges on weights
            pq.addAll( allEdges );

            //create a parent []
            int[] parent = new int[vertices];

            //makeset
            makeSet( parent );

            ArrayList<Edge> mst = new ArrayList<>();

            //process vertices - 1 edges
            int index = 0;
            while (index < vertices - 1) {
                Edge edge = pq.remove();
                //check if adding this edge creates a cycle
                int x_set = find( parent, edge.c1 );
                int y_set = find( parent, edge.c2 );

                if (x_set != y_set) {
                    //add it to our final result
                    mst.add( edge );
                    index++;
                    union( parent, x_set, y_set );
                }
            }
            //print MST
            printGraph( mst );
        }

        void makeSet(int[] parent) {
            //Make set- creating a new element with a parent pointer to itself.
            for (int i = 0; i < vertices; i++) {
                parent[i] = i + 1;
            }
        }

        int find(int[] parent, int vertex) {
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if (parent[vertex - 1] != vertex)
                return find( parent, parent[vertex - 1] );
            return vertex;
        }

        void union(int[] parent, int x, int y) {
            int x_set_parent = find( parent, x );
            int y_set_parent = find( parent, y );
            //make x as parent of y
            parent[y_set_parent - 1] = x_set_parent;
        }

        void printGraph(ArrayList<Edge> edgeList) {
            long answer = 0;
            for (Edge edge : edgeList) {
                if (edge.price != 0) {
                    if (answer == 0) {
                        answer = 1;
                    }
                    answer *= edge.price % (Math.pow( 2, 31 ) - 1);
                }
            }

            System.out.println( answer );
        }
    }

    private static Set<Integer> compSet = new HashSet<>();

    public static void main(String[] args) {
        //given
        Scanner scanner = new Scanner( System.in );
        String[] inputArr = scanner.nextLine().split( " " );

        int N = Integer.valueOf( inputArr[0] );
        int K = Integer.valueOf( inputArr[1] );
        int M = Integer.valueOf( inputArr[2] );

        if (N <= 0) {
            System.out.println( "-1" );
            System.exit( 0 );
        }
        if (N >= 1000000) {
            System.out.println( "-1" );
            System.exit( 0 );
        }
        if (K < 0) {
            System.out.println( "-1" );
            System.exit( 0 );
        }
        if (K >= 1000000) {
            System.out.println( "-1" );
            System.exit( 0 );
        }
        if (M < 0) {
            System.out.println( "-1" );
            System.exit( 0 );
        }
        if (M >= 1000000) {
            System.out.println( "-1" );
            System.exit( 0 );
        }

        Graph graph = new Graph( N );

        if (K > 0) {
            for (int i = 0; i < K; i++) {
                String[] mapPair = scanner.nextLine().split( " " );
                int c1 = Integer.valueOf( mapPair[0] );
                int c2 = Integer.valueOf( mapPair[1] );
                graph.addEgde( c1, c2, 0 );
                compSet.add( c1 );
                compSet.add( c2 );
            }
        }

        for (int i = 0; i < M; i++) {
            String[] mapPair = scanner.nextLine().split( " " );
            int c1 = Integer.valueOf( mapPair[0] );
            int c2 = Integer.valueOf( mapPair[1] );
            int price = Integer.valueOf( mapPair[2] );
            graph.addEgde( c1, c2, price );
            compSet.add( c1 );
            compSet.add( c2 );
        }

        if (compSet.size() != N) {
            System.out.println( "-1" );
            System.exit( 0 );
        }

        graph.kruskalMST();
    }
}
