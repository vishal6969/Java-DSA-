package DG;

import java.util.*;

public class DG {
    private int size;
    private ArrayList<ArrayList<Integer>> adj;
    private Stack<Integer> st;
    private boolean visited[];
    private boolean color[];
    private short status[];

    public DG(int size) {
        this.size = size;
        adj = new ArrayList<>();

        //initialise list for every vertex
        for(int i = 0; i < size; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }

    public void add(int u, int v) throws IndexOutOfBoundsException{

        if(u >= this.size || v >= this.size)
            throw new IndexOutOfBoundsException("vertex out of graph size.");
        adj.get(u).add(v);
    }

    //print adjacency list of graph 
    public void display() {

        for(int i = 0; i < this.size; i++) {
            System.out.print(i+"->");
            for(int j : adj.get(i)) {
                System.out.print(j+"->");
            }
            System.out.println();
        }
    }

    public int countComponents() {

        ArrayList<ArrayList<Integer>> newAdj = new ArrayList<>();
        int count = 0;

        for(int i = 0; i < this.size; i++) {
            newAdj.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < adj.size(); i++) {
            for(int j : adj.get(i)) {
                newAdj.get(j).add(i);
            }
        }

        Stack<Integer> st = topological_order(newAdj);

        visited = new boolean[this.size];

        while(!st.isEmpty()) {
            int temp = st.pop();
            if(!visited[temp]) {
                dfs(temp, adj);
                count++;
            }
        }
        return count;
    }

    public boolean hasCycle() {
        boolean temp = false;
        status = new short[this.size];

        for(int i = 0; i < this.size; i++) {
            if(status[i] == 0) {
                temp |= dfs(i, i);
            }
        }

        return temp;
    }

    public boolean isBipartite() {

        color = new boolean[this.size];
        visited = new boolean[this.size];
        boolean temp = false;

        for(int i = 0; i < this.size; i++) {
            if(!visited[i]) {
                temp |= dfs(i, true);
            }
        }

        return !temp;
    }

    public Stack<Integer> topological_order() {
        return topological_order(this.adj);
    }

    private Stack<Integer> topological_order(ArrayList<ArrayList<Integer>> adj) {
        st = new Stack<Integer>();
        visited = new boolean[this.size];

        for(int i = 0; i < this.size; i++) {
            if(!visited[i])
                dfs(i, adj);
        } 
        return st;
    }

    private void dfs(int i, ArrayList<ArrayList<Integer>> adj) {
        if(visited[i]) {
            return;
        }

        visited[i] = true;
        
        for(int x: adj.get(i)) {
            dfs(x, adj);
        }

        st.push(i);
    }

    private boolean dfs(int p, int i) {

        status[i] = 1;
        boolean temp = false;

        for(int n : adj.get(i)) {
            if(status[n] == 0)
                temp |= dfs(i, n);
            else if(status[n] == 1 && n != p)
                return true;
        }

        status[i] = 2;
        return temp;
    }

    private boolean dfs(int i, boolean c) {
        if(visited[i]) {
            if(color[i] != c)
                return true;
            return false;
        }

        color[i] = c;
        visited[i] = true;
        boolean temp = false;

        for(int n : adj.get(i)) {
            temp |= dfs(n, !c);
        }

        return temp;
    }
}
