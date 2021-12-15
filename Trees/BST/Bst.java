import java.util.*;

class Bst<K, V>
{
    private int size;
    private Comparator<Node<K, V>> cmp;
    private Node<K, V> root;

    //default constructor
    Bst() {
        this.size = 0;
        this.cmp = null;
    }

    //constructor with comparator
    Bst(Comparator<Node<K, V>> cmp) {
        this.size = 0;
        this.cmp = cmp;
    }

    public void add(K key, V value) {
        //check if user has passed comparator
        if(cmp == null) {
            this.root = add(this.root, new Node<K, V>(key, value));
        }
        else {
            this.root = add(this.root, new Node<K, V>(key, value, cmp));
        }

        this.size++;
    }

    private Node<K, V> add(Node<K, V> root, Node<K, V> node) {

        if(root == null) {
            return node;
        }

        if(root.compareTo(node) > 0) {
            root.left = add(root.left, node);
        }
        else {
            root.right = add(root.right, node);
        }

        return root;
    } 

    public V remove(K key) {
        Node<K, V> temp = get(this.root, new Node<K, V>(key, null));
        remove(this.root, temp);

        return temp.getValue();
    }

    private Node<K, V> remove(Node<K, V> root, Node<K, V> node) {
        if(root == null) {
            return null;
        }
        if(root.compareTo(node) > 0) {
            root.left = remove(root.left, node);
            return root;
        }
        else if(root.compareTo(node) < 0) {
            root.right = remove(root.right, node);
            return root;
        }

        Node<K, V> temp = getMin(root.right);
        if(temp != null) {
            temp.left = root.left;
            temp.right = root.right;
        }
        else {
            temp = root.left;
        }
        
        return temp;
    }

    public V get(K key) {
        return get(this.root, new Node<K, V>(key, null)).getValue();
    }

    private Node<K, V> get(Node<K, V> root, Node<K, V> node) {
        if(root == null) {
            throw new NoSuchElementException();
        }
        if(root.compareTo(node) > 0) {
            return get(root.left, node);
        }
        else if(root.compareTo(node) == 0){
            return root;
        }
        else {
            return get(root.right, node);
        }
    }

    public V getMin() {
        return getMin(this.root).getValue();
    }

    private Node<K, V> getMin(Node<K, V> root) {
        if(root == null) {
            return null;
        }

        Node<K, V> temp = getMin(root.left);
        return temp == null ? root : temp; 
    }

    public V getMax() {
        return getMax(this.root).getValue();
    }

    private Node<K, V> getMax(Node<K, V> root) {
        if(root == null) {
            return null;
        }

        Node<K, V> temp = getMin(root.right);
        return temp == null ? root : temp; 
    }

    public int getSize() {
        return this.size;
    }

    //inorder traversal of bst
    public void traverse() {
        System.out.println();
        traverse(this.root);
    }

    private void traverse(Node<K, V> root) {
        if(root == null) {
            return;
        }

        traverse(root.left);
        System.out.print(root.getValue()+" ");
        traverse(root.right);
    }

    public int getDiameter() {
        return getDiameter(this.root);
    }

    private int getDiameter(Node<K, V> root) {
        return getRightRadius(root, 0) + getLeftRadius(root, 0);
    }

    private int getLeftRadius(Node<K, V> root, int d) {
        if(root == null) {
            return d;
        }
        return Math.max(getLeftRadius(root.left, d+1), getLeftRadius(root.right, d-1));
    }

    private int getRightRadius(Node<K, V> root, int d) {
        if(root == null) {
            return d;
        }
        return Math.max(getRightRadius(root.right, d+1), getRightRadius(root.left, d-1));
    }
}