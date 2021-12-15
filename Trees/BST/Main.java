import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        
        
        Scanner scan = new Scanner(new FileInputStream("X:\\Java\\Trees\\BST\\input.txt"));
        Bst<Integer, String> b = new Bst<>();

        while(scan.hasNextLine()) {
            b.add(scan.nextInt(), scan.next());
        }

        b.traverse();
        b.remove(0);
        b.traverse();
        b.remove(150);
        b.traverse();
        b.add(15, "dumdum");
        b.traverse();
        scan.close();
    }
}
