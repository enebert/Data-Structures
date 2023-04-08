public class Node{

    private Node prevNode;
    private Node nextNode;

    private int data;

    int size;

    public Node(){
        prevNode = null;
        nextNode = null;

        data = 0;
    }

    public Node(int value){
        prevNode = null;
        nextNode = null;

        this.data = value;
    }

    public int getData(){
        return data;
    }

    public void setData(int value){
        this.data = value;
    }

    public Node getNext(){
        return nextNode;
    }
    public void setNext(Node next){
        nextNode = next;
    }
}
