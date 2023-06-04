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

    @Override
    public String toString(){
        return "(" + tail + "," + head + ")";
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Edge)) return false;

        Edge e = (Edge) o;
        return (e.getHead().equals(this.head) && e.getTail().equals(this.tail));
    }

    public T getHead(){
        return head;
    }
    
    public T getTail(){
        return tail;
    }
}