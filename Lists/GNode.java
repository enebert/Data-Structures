public class GNode<T> {

    private GNode<T> prevNode;
    private GNode<T> nextNode;

    private T data;

    int size;

    public GNode(){
        prevNode = null;
        nextNode = null;

        data = null;
        size = 0;
    }

    public GNode(T value){
        prevNode = null;
        nextNode = null;

        this.data = value;
    }

    public T getData(){
        return data;
    }

    public void setData(T value){
        this.data = value;
    }

    public GNode<T> getNext(){
        return nextNode;
    }

    public void setNext(GNode<T> next){
        nextNode = next;
    }

    public GNode<T> getPrevious(){
        return prevNode;
    }

    public void setPrevious(GNode<T> prev){
        prevNode = prev;
    }
}
    