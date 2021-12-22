import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new FileInputStream("X:\\Java\\Trees\\AVL\\input.txt"));
        AVL<Integer, String> b = new AVL<>();

        while (scan.hasNextLine()) {
            b.add(scan.nextInt(), scan.next());
        }

        b.traverse();
        System.out.println("Height: "+b.height());
        scan.close();
    }
}
