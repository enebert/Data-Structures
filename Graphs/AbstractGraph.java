import java.util.*;

public interface AbstractGraph<T> {
    
    abstract boolean insertVertex(T v);
    abstract boolean deleteVertex(T v, boolean f);
    abstract boolean insertEdge(Edge<T> e, boolean f);
    abstract boolean deleteEdge(Edge<T> e, boolean f);
    abstract AbstractGraph subgraph(ArrayList<T> vertices);
}