import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
    public static void main(String[] args) throws FileNotFoundException {
       
        Scanner scan = new Scanner(new FileInputStream("X:\\Java\\Graph\\Kruskal-Algo\\input.txt")); 

        int n = scan.nextInt();
        UDG graph = new UDG(n);
        UnionFind uf = new UnionFind(n);
        MyPriorityQueue<ArrayList<Integer>> pq = new MyPriorityQueue<>(new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                return b.get(2) - a.get(2);
           } 
        });

        while (scan.hasNextLine()) {

            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(scan.nextInt());
            temp.add(scan.nextInt());
            temp.add(scan.nextInt());

            pq.add(temp);
        }
        
        while (pq.getSize() != 0) {
            ArrayList<Integer> temp = pq.remove();
            int u = temp.get(0);
            int v = temp.get(1);

            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                graph.add(u, v);
            }
        }

        graph.display();
    }
}
