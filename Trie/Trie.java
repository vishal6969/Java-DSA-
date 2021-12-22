public class Trie<E> {
    Node<E> root;

    Trie() {
        this.root = new Node<E>(null);
    }

    public void add(String key, E value) {
        add(this.root, 0, key, value);
    }

    private void add(Node<E> node, int i, String key, E value) {
        if(i >= key.length()) {
            node.data = value;
            return;
        }
        if(node.next[key.charAt(i)] == null) {
            node.next[key.charAt(i)] = new Node<>(null);
        }
        add(node.next[key.charAt(i)], i+1, key, value);
    }

    public E get(String key) {
        return get(this.root, 0, key);
    }

    private E get(Node<E> node, int i, String key) {
        if(i >= key.length()) {
            return node.data;
        }
        if(node.next[key.charAt(i)] == null) {
            return null;
        }

        return get(node.next[key.charAt(i)], i+1, key);
    }

    public E remove(String key) {
        E temp = get(key);
        add(key, null);

        return temp;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }
}
