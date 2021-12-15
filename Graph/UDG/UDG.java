package UDG;

import java.util.*;

public class UDG {
    
    private int size;
    private ArrayList<ArrayList<Integer>> adj;
    private boolean visited[];
    private boolean color[];

    public UDG(int size) {
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
        adj.get(v).add(u);
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

        int count = 0;
        visited = new boolean[this.size];

        for(int i = 0; i < this.size; i++) {
            if(!visited[i]) {
                dfs(i);
                count++;         //increment component count 
            }
        }

        return count;
    }

    public boolean hasCycle() {
        boolean temp = false;
        visited = new boolean[this.size];

        for(int i = 0; i < this.size; i++) {
            if(!visited[i]) {
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

    private void dfs(int i) {
        if(visited[i]) {
            return;
        }

        visited[i] = true;

        for(int n : adj.get(i)) {
            dfs(n);
        }
    }

    private boolean dfs(int p, int i) {

        visited[i] = true;
        boolean temp = false;

        for(int n : adj.get(i)) {
            if(!visited[n])
                temp |= dfs(i, n);
            else if(n != p)
                return true;
        }

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
