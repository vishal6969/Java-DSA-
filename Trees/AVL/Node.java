import java.util.*;

public class Node<K, V> implements Comparable<Node<K,V>>{
    K key;
    V value;
    Node<K,V> left;
    Node<K,V> right;
    int height;
    Comparator<K> cmp;

    Node(K key, V value, Comparator<K> cmp) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.cmp = cmp;
    }

    @Override
    public int compareTo(Node<K,V> node) {
        
        return this.cmp.compare(this.key, node.key);
    }
}
