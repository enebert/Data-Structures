import java.util.*;

public class WeightedGraph<T> extends Graph<T,Edge<T>>{
    
    public WeightedGraph(){
        adjacencyList = new Hashtable<>();
        numVertices = 0;
        numEdges = 0;

    }
    
   public boolean deleteVertex(T vertex, boolean directed){
        if(!adjacencyList.containsKey(vertex)) return false;
        
        if(directed){
            adjacencyList.remove(vertex);
            Set<T> temp = adjacencyList.keySet();
            for(T item : temp){
                if(adjacencyList.get(item).contains(new Edge<T>(item, vertex))){
                    adjacencyList.get(item).remove(new Edge<T>(item, vertex));
                }     
            }
        }else{
            LinkedList<Edge<T>> temp=adjacencyList.get(vertex);
            adjacencyList.remove(vertex);
            for(Edge<T> item : temp){
                if(adjacencyList.containsKey(item.getHead())){
                    adjacencyList.get(item.getHead()).remove(new Edge<T>(vertex, item.getHead()));
                }else{
                    adjacencyList.get(item.getTail()).remove(new Edge<T>(item.getTail(), vertex));
                }
            }
        }
        numVertices--;
        return true;
    }
    
    public LinkedList<T> getAdjacent(T vertex){
        LinkedList<Edge<T>> temp = adjacencyList.get(vertex);
        LinkedList<T> v = new LinkedList<>();

        for(Edge<T> t : temp){
            if(t.getTail().equals(vertex)){
                v.add(t.getHead());
            }else{
                v.add(t.getTail());
            }
        }
        return v;
    }
    
    public void addAllEdges(ArrayList<Edge<T>> edgeList, boolean directed){
        for(Edge<T> edge : edgeList){
            if(!adjacencyList.contains(edge.getHead())) insertVertex(edge.getHead());
            if(!adjacencyList.containsKey(edge.getTail())) insertVertex(edge.getTail());

            insertEdge(edge, directed);
        }
    }
    
    public boolean insertEdge(Edge<T> e, boolean directed){
        
       if(adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
           if(directed){
               adjacencyList.get(e.getTail()).add(e);
           }else{
               adjacencyList.get(e.getTail()).add(e);
               adjacencyList.get(e.getHead()).add(e);
           }
           numEdges++;
           return true;
       }
        return false;
    }
    
    public boolean deleteEdge(Edge<T> e, boolean directed){
        if(!(adjacencyList.size()==0) || adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).remove(e);
            }else{
                Edge<T> t = new Edge<T> (e.getHead(), e.getTail());
                adjacencyList.get(e.getTail()).remove(e);
                adjacencyList.get(e.getHead()).remove(e);
                
                adjacencyList.get(e.getTail()).remove(t);
                adjacencyList.get(e.getHead()).remove(t);
            }
            numEdges--;
            return true;
        }
        return false;
    }
    
    public boolean containsEdge(Edge<T> e){
        if(adjacencyList.size()==0 || !adjacencyList.containsKey(e.getTail())) return false;
        return adjacencyList.get(e.getTail()).contains(e);
    }
    
     public WeightedGraph<T> subgraph(ArrayList<T> vertices){
        WeightedGraph<T> sub = new WeightedGraph<>();
        sub.addAllVertices(vertices);

        for(T item : vertices){
            for(Edge<T> e : adjacencyList.get(item)){
                if(e.getTail().equals(item) || e.getHead().equals(item)) sub.insertEdge(e, true);
            }
        }
         return sub;
    }
}