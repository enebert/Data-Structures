public class Edge<T> {
    private T head;
    private T tail;
    
    private int weight;

    public Edge(){
        head = null;
        tail = null;
    }
    
    public Edge(T t, T h){
        head = h;
        tail = t;
    }
    
    public Edge(T t, T h, int w){
        head = h;
        tail = t;
        weight = w;
    }

    public T getHead(){
        return head;
    }
    
    public T getTail(){
        return tail;
    }
}