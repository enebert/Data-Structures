import java.util.*;

public class SparseGraph<T> extends Graph<T,T>{
    
    public SparseGraph(){
        numVertices = 0;
        numEdges = 0;
        
        adjacencyList = new Hashtable<>();
    }
    
    public void addAllEdges(ArrayList<Edge<T>> edgeList, boolean directed){
        for(Edge<T> edge : edgeList){
            if(!adjacencyList.contains(edge.getHead())) insertVertex(edge.getHead());
            if(!adjacencyList.containsKey(edge.getTail())) insertVertex(edge.getTail());

            insertEdge(edge, directed);
        }
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
    
    public Graph<T,T> subgraph(ArrayList<T> vertices){
        SparseGraph<T> sub = new SparseGraph<>();
        sub.addAllVertices(vertices);

        for(T item : vertices){
            for(T v : adjacencyList.get(item)){
                if(vertices.contains(v)) sub.insertEdge(new Edge<T>(item,v), true);
            }
        }
        return sub;
    }

    public LinkedList<T> getAdjacent(T vertex){
        return adjacencyList.get(vertex);
    }
}