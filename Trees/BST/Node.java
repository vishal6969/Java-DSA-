import java.util.Comparator;

public class Node<K, V> implements Comparable<Node<K, V>>{
    
    private K key;
    private V value;
    Node<K, V> left;
    Node<K, V> right;
    Comparator<Node<K, V>> cmp;

    //default constructor
    Node(K key, V value) {

        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;

        //default comparator
        cmp = new Comparator<Node<K, V>>() {

            public int compare(Node<K, V> a, Node <K, V> b) {
                Integer i1 = (Integer) a.getKey();
                Integer i2 = (Integer) b.getKey();
                return i1 - i2;
            }
        };
    }

    //constructor with user defined comparator
    Node(K key, V value, Comparator<Node<K, V>> cmp) {

        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.cmp = cmp;
    }

    K getKey() {
        return this.key;
    }

    V getValue() {
        return this.value;
    }
    @Override
    public int compareTo(Node<K, V> n) {
        return cmp.compare(this, n);
    }
}
