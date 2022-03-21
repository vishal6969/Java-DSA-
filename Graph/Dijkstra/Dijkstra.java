import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {

    static ArrayList<ArrayList<pair>> adj;

    public static int[] getMinDistanceArray(int start) {

        int[] distance = new int[adj.size()];
        boolean[] visited = new boolean[adj.size()];

        //priority queue based on minimum distance from source
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {

                    public int compare(Integer a, Integer b) {

                        return distance[a] >= distance[b] ? 1 : -1;
                    }
                });

        //initially all vertices are infinitely far 
        //from source(except source itself)
        for (int i = 0; i < distance.length; i++) {

            if (i != start)
                distance[i] = Integer.MAX_VALUE;
            pq.add(i);
        }

        while (!pq.isEmpty()) {

            int temp = pq.remove();
            visited[temp] = true;

            //update minimum distance for all neighbouring vertices
            for (pair i : adj.get(temp)) {

                if (!visited[i.vertex]) {

                    pq.remove(i.vertex);
                    distance[i.vertex] = Math.min(distance[i.vertex],
                            distance[temp] + i.weight);
                    pq.add(i.vertex);
                }
            }
        }

        return distance;
    }

    //driver code
    public static void main(String[] args) throws Exception {

        //take input from file
        Scanner scan = new Scanner(new File("X:\\Java\\Graph\\Dijkstra\\input.txt"));
        int vertices = scan.nextInt();
        int edges = scan.nextInt();
        adj = new ArrayList<>();

        //initialise empty Adjacency list
        for (int i = 0; i < vertices; i++) {

            adj.add(new ArrayList<pair>());
        }

        //add edges
        while (edges-- != 0) {

            int u = scan.nextInt();
            int v = scan.nextInt();
            int weight = scan.nextInt();

            adj.get(u).add(new pair(v, weight));
            adj.get(v).add(new pair(u, weight));
        }

        int[] minDist = getMinDistanceArray(0);

        //print minimum distance array
        for (int i = 0; i < minDist.length; i++) {
            System.out.print(minDist[i] + " ");
        }
    }
}

//pair of vertex and edge weight
class pair {
    int vertex;
    int weight;

    pair(int vertex, int weight) {

        this.vertex = vertex;
        this.weight = weight;
    }
}
