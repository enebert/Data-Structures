public class Edge<T> {
    private T head;
    private T tail;
    
    public Edge(){
        head = null;
        tail = null;
    }
    
    public Edge(T t, T h){
        head = h;
        tail = t;
    }
    
    public T getHead(){
        return head;
    }
    
    public T getTail(){
        return tail;
    }
}