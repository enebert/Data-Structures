import java.util.*;

public interface Graph<T> {
    
    abstract boolean insertVertex(T v);
    abstract boolean deleteVertex(T v, boolean f);
    abstract boolean insertEdge(Edge<T> e, boolean f);
    abstract boolean deleteEdge(Edge<T> e, boolean f);
    
    abstract boolean containsEdge(Edge<T> e);
    
    abstract Graph subgraph(ArrayList<T> vertices);
}