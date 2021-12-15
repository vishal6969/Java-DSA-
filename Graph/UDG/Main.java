package UDG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        
        
        Scanner scan = new Scanner(new FileInputStream("X:\\Java\\Graph\\UDG\\input.txt"));
        int n = scan.nextInt();
        UDG g = new UDG(n);

        while(scan.hasNextLine()) {
            g.add(scan.nextInt(), scan.nextInt());
        }

        g.display();

        System.out.println("Bipartite: "+g.isBipartite());
        System.out.println("Components: "+g.countComponents());
        System.out.println("Cyclic: "+g.hasCycle());
        scan.close();
    }
}
