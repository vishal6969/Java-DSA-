import java.util.Random;

public class Main {
    
    public static void main(String[] args) throws Exception{
        
        // HashMap<String, Integer> hm1 = new HashMap<>();

        // hm1.put("hello", 69);
        // System.out.println(hm1.get("hello"));
        // hm1.put("hello", 96);
        // System.out.println(hm1.get("hello"));

        HashMap<Integer, Integer> hm1 = new HashMap<>();
        Random r = new Random();
        // for (int i = 0; i < 3; i++) {
            int temp = Math.abs(r.nextInt());
            hm1.put(temp, 1);
            System.out.println("Found: "+hm1.get(temp));
            hm1.display();
        // }
    }
}
