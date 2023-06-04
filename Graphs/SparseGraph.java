import java.util.*;

public class SparseGraph<T> implements Graph<T>{
    
    private Hashtable<T, LinkedList<T>> adjacencyList;
    private int numVertices;
    private int numEdges;
    
    public SparseGraph(){
        numVertices = 0;
        numEdges = 0;
        
        adjacencyList = new Hashtable<>();
    }
    
    public String toString(){
        String list = "";
        for(T k : adjacencyList.keySet()){
            list = list + k + ": " + adjacencyList.get(k) + "\n";
        }
        return list;
    }
    
    public void addAllEdges(ArrayList<Edge<T>> edgeList, boolean directed){
        for(Edge<T> edge : edgeList){
            if(!adjacencyList.contains(edge.getHead())) insertVertex(edge.getHead());
            if(!adjacencyList.containsKey(edge.getTail())) insertVertex(edge.getTail());

            insertEdge(edge, directed);
        }
    }

    public boolean insertVertex(T key){
        if(adjacencyList.containsKey(key)){
            return false;
        }
        adjacencyList.put(key, new LinkedList<T>());
        this.numVertices++;
        return true;
    }
    
    public void addAllVertices(ArrayList<T> vList){
        for(T v : vList) this.insertVertex(v);
    }

    public boolean deleteVertex(T key, boolean directed){
        
        if(!adjacencyList.containsKey(key)) return false;
        
        if(directed){
            adjacencyList.remove(key);
            Set<T> temp = adjacencyList.keySet();
            for(T item : temp){
                if(adjacencyList.get(item).contains(key)) adjacencyList.get(item).remove(key);    
            }
        }else{
            LinkedList<T> temp=adjacencyList.get(key);
            adjacencyList.remove(key);
            for(T item : temp){
                adjacencyList.get(item).remove(key);
            }
        }
        numVertices--;
        return true;
    }
    
    public boolean insertEdge(Edge<T> e, boolean directed){
        if(adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).add(e.getHead());
            }else{
                adjacencyList.get(e.getTail()).add(e.getHead());
                adjacencyList.get(e.getHead()).add(e.getTail());
            }
            numEdges++;
            return true;
        }
        return false;
    }
    
    public boolean insertEdge(ArrayList<Edge<T>> edgeList, boolean directed){
        for(Edge<T> e : edgeList) if(!insertEdge(e, directed)) return false;
        return true;
    }

    public boolean deleteEdge(Edge<T> e, boolean directed){
        if(adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).remove(e.getHead());
            }else{
                adjacencyList.get(e.getTail()).remove(e.getHead());
                adjacencyList.get(e.getHead()).remove(e.getTail());
            }
            numEdges--;
            return true;
        }
        return false;
    }
    
    public boolean containsEdge(Edge<T> e){
        return adjacencyList.get(e.getTail()).contains(e.getHead());
    }
    
    public Graph<T> subgraph(ArrayList<T> vertices){
        SparseGraph<T> sub = new SparseGraph<>();
        sub.addAllVertices(vertices);

        for(T item : vertices){
            for(T v : adjacencyList.get(item)){
                if(vertices.contains(v)) sub.insertEdge(new Edge<T>(item,v), true);
            }
        }
        return sub;
    }
    
    public int getNumVertices(){
        return numVertices;
    }
    
    public int getNumEdges(){
        return numEdges;
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

    public static SparseGraph<Integer> random(int numVertices){
        Random gen = new Random();
        int t;
        int h;
        Edge<Integer> e; Edge<Integer> f;
        SparseGraph<Integer> randGraph = new SparseGraph<>();
        ArrayList<Edge<Integer>> edgeList = new ArrayList<>();

        int numEdges = gen.nextInt(numVertices*(numVertices-1)/2) + 1;

        for(int i=0; i < numEdges; i++){
            do{
                t = gen.nextInt(numVertices);
                h = gen.nextInt(numVertices);
                e = new Edge<Integer>(t,h);
                f = new Edge<Integer>(h,t);
            }while(edgeList.contains(e) || edgeList.contains(f) || t==h);
            edgeList.add(e);
        }
        
        randGraph.addAllEdges(edgeList, false);

        return randGraph;
    }
}