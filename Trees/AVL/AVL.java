import java.util.Comparator;

public class AVL<K, V>
{
    Node<K, V> root;
    Comparator<K> cmp;

    AVL() {
        this.root = null;
        this.cmp = null;

        //default comparator
        this.cmp = new Comparator<K>(){
            public int compare(K a, K b) {
                int temp1 = (int) a;
                int temp2 = (int) b;
            
                return temp1 - temp2;
            }
        };
    }
    AVL(Comparator<K> cmp) {
        this.root = null;
        this.cmp = cmp;
    }

    public void add(K key, V value) {

        this.root = add(this.root, new Node<K,V>(key, value, this.cmp));
    }

    private Node<K,V> add(Node<K,V> root, Node<K,V> node) {
        if(root == null) {
            return node;
        }

        if(root.compareTo(node) > 0) {
            root.left = add(root.left, node);
        }
        else {
            root.right = add(root.right, node);
        }

        //when tree is unbalanced
        int hl = height(root.left);
        int hr = height(root.right);
        if(Math.abs(hr - hl) > 1) {
            Node<K,V> x = null;

            if(hl > hr) {
                int hll = height(root.left.left);
                int hlr = height(root.left.right);
                if(hll > hlr) {
                    x = root.left;
                    root.left = x.right;
                    x.right = root;
                }
                else {
                    
                }
            }
            else {
                if(root.right.left == null) {
                    x = root.right;
                    x.left = root;
                    root.right = null;
                }
                else {
                    x = root.right.left;
                    x.right = root.right;
                    x.left = root;
                    root.right.left = null;
                    root.right = null;
                }
            }

            return x;
        }
        return root;
    }

    public V get(K key) {
        return get(this.root, key);
    }

    private V get(Node<K,V> root, K key) {
        if(root == null) {
            return null;
        }
        if(cmp.compare(root.key, key) > 0) {
            return get(root.left, key);
        } 
        else if(cmp.compare(root.key, key) == 0) {
            return root.value;
        }
        else {
            return get(root.right, key);
        }
    }

    public V remove(K key) {
        V value = get(this.root, key);
        this.root = remove(this.root, key);
        return value;
    }

    private Node<K,V> remove(Node<K,V> root, K key) {
        if(root == null) {
            return null;
        }
        if(cmp.compare(root.key, key) > 0) {
            root.left = remove(root.left, key);
        } 
        else if(cmp.compare(root.key, key) == 0) {
            if(root.left == null)
                return root.right;
            if(root.right == null)
                return root.left;

            Node<K,V> temp = getMin(root.right);
            temp.left = root.left;
            temp.right = root.right;

            return temp;
        }
        else {
            root.right = remove(root.right, key);
        }
        return root;
    }

    public V getMin(){
        return getMin(this.root).value;
    }

    private Node<K,V> getMin(Node<K,V> root) {
        if(root == null || root.left == null) {
            return root;
        }

        return getMin(root.left);
    }

    public V getMax(){
        return getMax(this.root).value;
    }

    private Node<K,V> getMax(Node<K,V> root) {
        if(root == null || root.right == null) {
            return root;
        }

        return getMax(root.right);
    }

    //inorder traversal of avl
    public void traverse() {
        System.out.println();
        traverse(this.root);
    }

    private void traverse(Node<K, V> root) {
        if(root == null) {
            return;
        }

        traverse(root.left);
        System.out.print(root.value+" ");
        traverse(root.right);
    }

    public int height() {
        return height(this.root);
    }

    private int height(Node<K,V> root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }
}
