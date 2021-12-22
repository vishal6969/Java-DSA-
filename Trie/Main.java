public class Main {
    public static void main(String[] args) {
        Trie<Integer> t = new Trie<>();

        t.add("aloo", 69);
        t.add("baigan", 96);

        System.out.println(t.get("aloo"));
        System.out.println(t.get("baigan"));
        System.out.println(t.contains("baigan"));
        System.out.println(t.contains("baigana"));
    }
}
