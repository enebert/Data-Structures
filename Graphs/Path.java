import java.util.*;

public class Path<T> {
    private LinkedList<Edge<T>> path;
    
    public Path(){
        path = new LinkedList<Edge<T>>();
    }
    
    @Override
    public String toString(){
        String p = "";
        for(Edge<T> e : path) p = e.toString() + p;
        return p;
    }
    
    public boolean isEmpty(){
        return path.size()==0;
    }
    
    public T getStart(){
        return path.peekLast().getTail();
    }
    
    public T getEnd(){
        return path.peekFirst().getHead();
    }
    
    public boolean add(Edge<T> e){
        if(path.size() == 0){
            path.addFirst(e);
            return true;
        }else if(path.size() > 0 && (path.peekFirst().getHead().equals(e.getTail()))){
            path.addFirst(e);
            return true;
        } 
        return false;
    }
    
    public Iterator<Edge<T>> iterator(){
        return path.descendingIterator();
    }
}