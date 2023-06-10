public class Edge<T> {
    private T head;
    private T tail;
    
    private double weight;

    public Edge(){
        head = null;
        tail = null;
        weight = 0.0;
    }
    
    public Edge(T t, T h){
        head = h;
        tail = t;
        weight = 0.0;
    }
    
    public Edge(T t, T h, double w){
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

    public double getWeight(){
        return weight;
    }
}