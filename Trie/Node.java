public class Node<E> {
    E data;
    Node<E> next[];

    Node(E data) {
        this.data = data;
        this.next = new Node[128];
    }
}
