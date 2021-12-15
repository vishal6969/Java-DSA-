import java.util.*;

public class MyPriorityQueue<E> implements Iterable<E>{

    private ArrayList<E> elements;
    private int count = 0;
    
    //default comparator
    Comparator<E> cmp = new Comparator<E>() {

        public int compare(E a, E b) {
            
            Integer n1 = (Integer) a;
            Integer n2 = (Integer) b;

            return n1 - n2; 
        }
    };

    public MyPriorityQueue() {

        elements = new ArrayList<>();
        elements.add(null);
    }

    public MyPriorityQueue(Comparator<E> cmp) {

        this.cmp = cmp;
        elements = new ArrayList<>();
        elements.add(null);
    }

    private void replace(int i, int j) {

        E temp = elements.get(i);

        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }


    public void add(E n) {

        //resize
        elements.add(n);
        count++;

        //swim
        for(int i = count; i / 2 > 0; i /= 2) {
            
            if(cmp.compare(n, elements.get(i / 2)) > 0) {
                replace(i, i / 2);
            }
            else {
                break;
            }
        }
    }

    public E peek() {

        if(count == 0) {
            throw new NoSuchElementException();
        }

        return elements.get(1);
    }

    public E get(int i) {

        if(i + 1 > count) {
            throw new NoSuchElementException();
        }

        return elements.get(i + 1);
    }

    public E remove() {

        if(count == 0) {
            throw new NoSuchElementException();
        }

        //replace first and last 
        E temp = elements.get(1);
        replace(1, count);

        //remove last
        elements.remove(count);
        count--;

        //sink
        for(int i = 1; 2 * i <= count; i *= 2) {

            if(cmp.compare(elements.get(i), elements.get(2 * i) ) < 0) {
                replace(i, 2 * i);
            }
            if( ((2 * i) + 1) <= count && cmp.compare(elements.get(i), elements.get( (2 * i) + 1) ) < 0) {
                replace(i, (2 * i) + 1);
            }
            else {
                break;
            }
        }
        return temp;
    }

    public int getSize() {

        return this.count;
    }
    
    //implement iterable
    public Iterator<E> iterator() {

        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<E> 
    {
        private ArrayList<E> pq;

        CustomIterator() {

            pq = new ArrayList<>();

            for(E i : elements) {
                pq.add(i);
            }
        }

        public boolean hasNext() {

            return pq.size() > 1;
        }

        public E next() {

            if(!hasNext()) {

                throw new NoSuchElementException();
            }

            return pq.remove(1);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    //driver code
    public static void main(String[] args) {
        
        MyPriorityQueue<Integer> pq = new MyPriorityQueue<>();

        pq.add(96);
        pq.add(69);
        pq.add(169);
        pq.add(1);
        pq.add(-1);
        pq.add(-69);
        pq.add(34);
        pq.add(101);

        // System.out.println(pq.getSize());
        for(int i: pq) {
            System.out.print(i + " ");
        }

        System.out.println("\nOrdered");

        while(pq.getSize() > 0) {
            System.out.print(pq.remove()+" ");
        }
    }
}