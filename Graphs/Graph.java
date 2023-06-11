import java.util.*;

public abstract class Graph<T, V> implements AbstractGraph<T>{

    protected Hashtable<T, LinkedList<V>> adjacencyList;
    protected int numVertices;
    protected int numEdges;
    
    abstract boolean containsEdge(Edge<T> e);

    @Override
    public String toString(){
        String list = "";
        for(T k : adjacencyList.keySet()){
            list = list + k + ": " + adjacencyList.get(k) + "\n";
        }
        return list;
    }

    public boolean insertVertex(T key){
        if(this.adjacencyList.containsKey(key)){
            return false;
        }
        this.adjacencyList.put(key, new LinkedList<V>());
        numVertices++;
        return true;
    }

    public void addAllVertices(ArrayList<T> vList){
        for(T v : vList) this.insertVertex(v);
    }

    public Set<T> getVertices(){
        return adjacencyList.keySet();
    }

    public int getNumEdges(){
        return numEdges;
    }

    public int getNumVertices(){
        return numVertices;
    }

    public int degree(T vertex){
        return adjacencyList.get(vertex).size();
    }

    public boolean hasEulerTour(){
        for(T key : adjacencyList.keySet()){
            if(degree(key) % 2 == 1)
                return false;
        }
        return true;
    }
}