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

    protected static class Pair<T>{
        private T component;
        private double cost;

        public Pair(){
            component = null;
            cost = 0.0;
        }

    protected Pair(T v, double c){
            component = v;
            cost = c;
        }

    public String toString(){
            return "(Component: " + component + ", Cost: " + cost + ")";
        }

    public boolean equals(Object o){
            if(o == this) return true;
            if(!(o instanceof Pair)) return false;

            Pair p = (Pair) o;
            return (component.equals(p.component)) && (Double.compare(cost, p.cost)==0);
        }

    public int hashCode(){
            final int prime = 22859;
            int result = 7691;
            result = prime*result + ((component==null) ? 0 : component.hashCode());

            return result;
        }

    public int compareTo(Object o){
            Pair p = (Pair) o;

            if(this.equals(0)) return 0;

            return Double.compare(this.cost, p.cost);
        }

    protected double getCost(){
            return cost;
        }

    protected T getComponent(){
            return component;
        }
    }
}