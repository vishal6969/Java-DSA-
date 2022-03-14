public class Main {
    
    public static void main(String[] args) throws Exception {
        
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i = 0; i < 255; i++)
            hm.put((char) i, i);
        
        for(int i = 255; i >= 0; i--)
        System.out.println(hm.get((char)i));
    }
}
