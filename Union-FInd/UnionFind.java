public class UnionFind {

    private int[] arr;

    UnionFind(int size) {
        arr = new int[size];
        //everyone is there own parent initially
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
    }

    public int find(int a) {
        if (arr[a] == a) {
            return a;
        }
        return find(arr[a]);
    }
    
    public void union(int a, int b) {
        arr[b] = find(a);
    }
}
